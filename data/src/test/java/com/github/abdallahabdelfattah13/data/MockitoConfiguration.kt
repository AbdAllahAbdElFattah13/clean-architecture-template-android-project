//package com.github.abdallahabdelfattah13.data
//
//import io.reactivex.Flowable
//import io.reactivex.Observable
//import io.reactivex.Single
//import org.mockito.configuration.DefaultMockitoConfiguration
//import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues
//import org.mockito.invocation.InvocationOnMock
//import org.mockito.stubbing.Answer
//
//
///**
// * Created by AbdAllah AbdElfattah on 18/06/2019,
// * The D. GmbH,
// * Cairo, Egypt.
// */
//class MockitoConfiguration : DefaultMockitoConfiguration() {
//    override fun getDefaultAnswer(): Answer<Any> {
//        return object : ReturnsEmptyValues() {
//            override fun answer(inv: InvocationOnMock): Any {
//                val type = inv.method.returnType
//                return when {
//                    type.isAssignableFrom(Single::class.java) -> Single.error<Any>(createException(inv))
//                    type.isAssignableFrom(Flowable::class.java) -> Flowable.error<Any>(createException(inv))
//                    type.isAssignableFrom(Observable::class.java) -> Observable.error<Any>(createException(inv))
//                    else -> super.answer(inv)
//                }
//            }
//        }
//    }
//
//    private fun createException(
//        invocation: InvocationOnMock
//    ): RuntimeException {
//        val s = invocation.toString()
//        return RuntimeException(
//            "No mock defined for invocation $s"
//        )
//    }
//}