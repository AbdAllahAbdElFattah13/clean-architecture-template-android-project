package com.github.abdallahabdelfattah13.data.util

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by AbdAllah AbdElfattah on 18/06/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
internal class OfflineFirstResourceTest {


    lateinit var flowableUnderTest: Flowable<Resource<String>>


    @Before
    fun setUp() {
        flowableUnderTest = object : OfflineFirstResource<String, Int>(
            apiSingle = Mocks.apiSingleOfInt,
            dbFlowable = Mocks.dbFlowableOfString,
            mainScheduler = Schedulers.trampoline(),
            workScheduler = Schedulers.trampoline()
        ) {
            override fun saveCallResult(item: Int) {
                Mocks.addToDb(item)
            }
        }.asFlowable()
    }

    @Test
    fun asFlowable() {
        val testFlowable = flowableUnderTest.test()

        testFlowable
            .assertSubscribed()
            .assertNoErrors()
            .assertValue(Resource.remoteSuccess("4"))
    }
}