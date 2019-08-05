package com.github.abdallahabdelfattah13.clean_architecture_template_project

import com.github.abdallahabdelfattah13.context.ContextModule
import com.github.abdallahabdelfattah13.data.RepositoriesModule
import com.github.abdallahabdelfattah13.domain.UseCasesModule
import com.github.abdallahabdelfattah13.presentation.ViewModelsFactoryModule
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 29/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        UseCasesModule::class,
        RepositoriesModule::class,
        ViewModelsFactoryModule::class
    ]
)
interface AppComponent {

    fun getOkHttpClient(): OkHttpClient

}