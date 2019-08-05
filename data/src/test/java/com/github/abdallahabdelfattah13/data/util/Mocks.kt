package com.github.abdallahabdelfattah13.data.util

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by AbdAllah AbdElfattah on 18/06/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
object Mocks {

    private val behaviorSubjectOfString = BehaviorSubject.create<String>()

    val apiSingleOfInt: Single<Int> = Single.just(4)
    val dbFlowableOfString: Flowable<String> = behaviorSubjectOfString.toFlowable(BackpressureStrategy.LATEST)

    init {
        behaviorSubjectOfString.onNext("12")
    }

    fun addToDb(item: Int) {
        behaviorSubjectOfString.onNext(item.toString())
    }

}