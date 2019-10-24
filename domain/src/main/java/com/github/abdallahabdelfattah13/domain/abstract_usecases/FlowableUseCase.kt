package com.github.abdallahabdelfattah13.domain.abstract_usecases

import io.reactivex.Flowable
import io.reactivex.Scheduler


/**
 * Created by AbdAllah AbdElfattah on 24/10/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
abstract class FlowableUseCase<Output, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun createFlowable(input: Input? = null): Flowable<Output>

    fun run(input: Input? = null): Flowable<Output> {
        return createFlowable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}