package com.github.abdallahabdelfattah13.clean_architecture_template_project

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.github.abdallahabdelfattah13.context.ContextModule


/**
 * Created by AbdAllah AbdElfattah on 29/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class App : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    private fun  initDagger(appContext: Context): AppComponent =
        DaggerAppComponent.builder()
            .contextModule(ContextModule(appContext))
            .build()

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this.applicationContext)
    }

}