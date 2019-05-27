package com.github.abdallahabdelfattah13.remote.retrofit

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class MainRetrofitProvider {

    fun provideRetrofit(
        baseUrl: String,
        client: OkHttpClient,
        gsonParser: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonParser))
            .build()

}