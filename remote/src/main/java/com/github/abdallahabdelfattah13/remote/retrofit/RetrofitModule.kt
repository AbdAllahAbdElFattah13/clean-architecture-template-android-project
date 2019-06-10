package com.github.abdallahabdelfattah13.remote.retrofit

import com.github.abdallahabdelfattah13.remote.network_client.OkHttpClientModule
import com.github.abdallahabdelfattah13.remote.parser.gson.GsonModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module(
    includes = [
        BaseUrlModule::class,
        OkHttpClientModule::class,
        GsonModule::class
    ]
)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: BaseUrlModule.BaseUrl,
        client: OkHttpClient,
        gsonParser: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl.url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonParser))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

}