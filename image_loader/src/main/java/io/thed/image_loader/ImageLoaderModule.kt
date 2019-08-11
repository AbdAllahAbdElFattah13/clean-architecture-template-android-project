package io.thed.image_loader

import com.github.abdallahabdelfattah13.remote.retrofit.BaseUrlModule
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.thed.image_loader.picasso_image_loader.PicassoImageLoader
import io.thed.image_loader.picasso_image_loader.PicassoModule
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 02/07/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */

@Module(
    includes = [
        BaseUrlModule::class,
        PicassoModule::class
    ]
)
class ImageLoaderModule {
    @Provides
    @Singleton
    fun providesPicassoImageLoader(
        picasso: Picasso,
        baseUrl: BaseUrlModule.BaseUrl
    ): ImageLoader = PicassoImageLoader(picasso, baseUrl.url)
}