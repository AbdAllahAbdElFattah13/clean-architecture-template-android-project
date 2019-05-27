package com.github.abdallahabdelfattah13.remote.network_client.interceptors

import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class TokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}