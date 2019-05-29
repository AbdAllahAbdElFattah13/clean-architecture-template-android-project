package com.github.abdallahabdelfattah13.remote.retrofit

import com.github.abdallahabdelfattah13.remote.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 28/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module
class BaseUrlModule {
    sealed class BaseUrl(val url: String) {
        class StagingUrl : BaseUrl("insert your staging server url here if needed!")
        class ProductionBaseUrl : BaseUrl("insert your production server url here if needed!")
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): BaseUrl =
        if (BuildConfig.DEBUG)
            BaseUrlModule.BaseUrl.StagingUrl()
        else
            BaseUrlModule.BaseUrl.ProductionBaseUrl()
}