package com.github.abdallahabdelfattah13.remote.network_client.authenticators

import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class RefreshTokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request()
    }
}

@Module
class RefreshTokenAuthenticatorModule() {

    @Provides
    @Singleton
    fun providesRefreshTokenAuthenticator(): Authenticator = RefreshTokenAuthenticator()
}