//v1.1

package com.github.abdallahabdelfattah13.data.util

import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by AbdAllah AbdElfattah on 13/06/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
abstract class _OfflineFirstResource<ResultType, RemoteType>
    (
    dbFlowable: Flowable<ResultType>, apiSingle: Single<RemoteType>,
    workScheduler: Scheduler, mainScheduler: Scheduler
) {

    private val result: Flowable<Resource<ResultType>> =
        dbFlowable
            .subscribeOn(workScheduler)
            .singleOrError()
            .observeOn(mainScheduler)
            .map { localData ->
                Resource.localSuccess(localData)
            }
            .flatMap { localData ->
                apiSingle.doOnError { t ->
                    Resource.error(localData, t, t.message ?: "")
                }
            }
            .toFlowable()

            .observeOn(workScheduler)
            .flatMap { remoteData ->
                saveCallResult(remoteData)
                dbFlowable
            }

            .observeOn(mainScheduler)
            .map { localData ->
                Resource.remoteSuccess(localData)
            }

            .observeOn(mainScheduler)
            .flatMap { dbFlowable }
            .map {
                Resource.localUpdate(it)
            }


    protected open fun onFetchFailed() {}

    @WorkerThread
    protected abstract fun saveCallResult(item: RemoteType)

    fun asFlowable(): Flowable<Resource<ResultType>> = result
}

abstract class dummy<ResultType, RemoteType>(
    dbFlowable: Flowable<ResultType>,
    apiSingle: Single<RemoteType>
) : _OfflineFirstResource<ResultType, RemoteType>
    (dbFlowable, apiSingle, Schedulers.io(), AndroidSchedulers.mainThread())
