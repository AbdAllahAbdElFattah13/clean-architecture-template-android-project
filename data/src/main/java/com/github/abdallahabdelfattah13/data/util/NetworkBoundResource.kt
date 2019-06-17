//v1.1

package com.github.abdallahabdelfattah13.data.util

import androidx.annotation.WorkerThread
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by AbdAllah AbdElfattah on 13/06/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
abstract class OfflineFirstResource<ResultType, RemoteType>
    (
    dbFlowable: Flowable<ResultType>, apiSingle: Single<RemoteType>,
    workScheduler: Scheduler, mainScheduler: Scheduler
) {

    private val resultSubject: BehaviorSubject<Resource<ResultType>> = BehaviorSubject.create()
    private val result: Flowable<Resource<ResultType>> = resultSubject.toFlowable(BackpressureStrategy.LATEST)

    //when do we need to disposable this exactly?
    private val disposable: Disposable

    init {

        resultSubject.onNext(Resource.loading())

        disposable = dbFlowable
            .subscribeOn(workScheduler)

            // will throw in case of closing the stream
            // without emitting any items from db,
            // but since room never closes the stream
            // until we dispose it "I guess!",
            //I think we are good to go!
            .singleOrError()

            .observeOn(mainScheduler)
            .flatMap { localResult ->
                //call shouldFetch?
                resultSubject.onNext(Resource.localSuccess(localResult))
                apiSingle
                    .doOnError {
                        resultSubject.onNext(Resource.error(localResult, it, it.message ?: "unknown error"))
                    }
            }
            .toFlowable() //do we really need this?

            .observeOn(workScheduler)
            .flatMap { remoteData ->
                saveCallResult(remoteData)
                dbFlowable
            }

            .observeOn(mainScheduler)
            .flatMap { localData ->
                resultSubject.onNext(Resource.remoteSuccess(localData))
                dbFlowable
            }
            .subscribe { localUpdate ->
                resultSubject.onNext(Resource.localUpdate(localUpdate))
            }

    }

    protected open fun onFetchFailed() {}

    @WorkerThread
    protected abstract fun saveCallResult(item: RemoteType)

    fun asFlowable(): Flowable<Resource<ResultType>> = result
}

abstract class NetworkBoundResource<ResultType, RemoteType>(
    dbFlowable: Flowable<ResultType>,
    apiSingle: Single<RemoteType>
) : OfflineFirstResource<ResultType, RemoteType>
    (dbFlowable, apiSingle, Schedulers.io(), AndroidSchedulers.mainThread())
