package com.github.abdallahabdelfattah13.remote.network_client

import com.github.abdallahabdelfattah13.remote.network_client.authenticators.RefreshTokenAuthenticator
import com.github.abdallahabdelfattah13.remote.network_client.authenticators.RefreshTokenAuthenticatorModule
import com.github.abdallahabdelfattah13.remote.network_client.interceptors.DaggerConstants
import com.github.abdallahabdelfattah13.remote.network_client.interceptors.InterceptorsModule
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module(
    includes = [
        RefreshTokenAuthenticatorModule::class,
        InterceptorsModule::class
    ]
)
class OkHttpClientModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @Named(DaggerConstants.TOKEN_INTERCEPTOR)
        tokenInterceptor: Interceptor,
        refreshTokenAuthenticator: RefreshTokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(refreshTokenAuthenticator)
            .addInterceptor(tokenInterceptor)
            .build()
    }

}