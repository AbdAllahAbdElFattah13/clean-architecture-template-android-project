package io.thed.image_loader.picasso_image_loader


import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 02/08/2018,
 * The D. GmbH,
 * Cairo, Egypt.
 */

@Module
class PicassoModule {

    @Provides
    @Singleton
    fun providesPicasso() = Picasso.get()
}