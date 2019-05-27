package com.github.abdallahabdelfattah13.remote.network_client

import com.github.abdallahabdelfattah13.remote.network_client.authenticators.RefreshTokenAuthenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class MainOkHttpClient {

    fun providesOkHttpClient(
        tokenInterceptor: Interceptor,
        refreshTokenAuthenticator: RefreshTokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(refreshTokenAuthenticator)
            .addInterceptor(tokenInterceptor)
            .build()
    }

}