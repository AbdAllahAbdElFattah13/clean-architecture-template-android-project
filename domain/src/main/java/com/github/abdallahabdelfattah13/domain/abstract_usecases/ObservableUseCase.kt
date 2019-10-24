package com.github.abdallahabdelfattah13.domain.abstract_usecases

import io.reactivex.Observable
import io.reactivex.Scheduler


/**
 * Created by AbdAllah AbdElfattah on 16/10/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
abstract class ObservableUseCase<Output, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun createObservable(input: Input? = null): Observable<Output>

    fun run(input: Input? = null): Observable<Output> {
        return createObservable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}