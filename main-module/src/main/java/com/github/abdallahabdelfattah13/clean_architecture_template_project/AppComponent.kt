package com.github.abdallahabdelfattah13.clean_architecture_template_project

import com.github.abdallahabdelfattah13.context.ContextModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 29/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Singleton
@Component(
    modules = [
        ContextModule::class
    ]
)
interface AppComponent {
}