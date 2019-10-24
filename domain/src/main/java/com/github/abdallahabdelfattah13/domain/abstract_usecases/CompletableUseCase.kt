package com.github.abdallahabdelfattah13.domain.abstract_usecases

import io.reactivex.Completable
import io.reactivex.Scheduler


/**
 * Created by AbdAllah AbdElfattah on 16/10/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
abstract class CompletableUseCase<in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun createCompletable(input: Input? = null): Completable

    fun run(input: Input? = null): Completable {
        return createCompletable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}