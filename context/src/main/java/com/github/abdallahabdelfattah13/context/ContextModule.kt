package com.github.abdallahabdelfattah13.context

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 29/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module
class ContextModule(private val appContext: Context) {

    @Provides
    @Singleton
    fun provideContext() = appContext
}