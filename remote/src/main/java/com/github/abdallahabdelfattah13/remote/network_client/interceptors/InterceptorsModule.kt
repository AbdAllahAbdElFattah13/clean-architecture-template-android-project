package com.github.abdallahabdelfattah13.remote.network_client.interceptors

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 28/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module
class InterceptorsModule {

    @Provides
    @Singleton
    @Named(DaggerConstants.TOKEN_INTERCEPTOR)
    fun providesTokenInterceptor(): Interceptor = TokenInterceptor()
}