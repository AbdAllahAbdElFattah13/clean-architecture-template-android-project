package com.github.abdallahabdelfattah13.clean_architecture_template_project

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.github.abdallahabdelfattah13.context.ContextModule
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import net.gotev.uploadservice.UploadService
import net.gotev.uploadservice.okhttp.OkHttpStack


/**
 * Created by AbdAllah AbdElfattah on 29/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class App : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    private fun initDagger(appContext: Context): AppComponent =
        DaggerAppComponent.builder()
            .contextModule(ContextModule(appContext))
            .build()

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this.applicationContext)

        RxPaparazzo
            .register(this)
//           .withFileProviderPath("Your selected media folder name goes here, might be your app name or something/")

        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID
        UploadService.HTTP_STACK = OkHttpStack(appComponent.getOkHttpClient())
    }

}