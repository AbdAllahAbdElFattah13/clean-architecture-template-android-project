package io.thed.image_loader

import android.net.Uri
import android.widget.ImageView


/**
 * Created by AbdAllah AbdElfattah on 02/08/2018,
 * The D. GmbH,
 * Cairo, Egypt.
 */
interface ImageLoader {

    fun loadImageInto(url: String?, targetImageView: ImageView)

    fun loadImageInto(
        url: String?,
        placeholderImageId: Int,
        targetImageView: ImageView,
        centerInside: Boolean = false,
        centerCrop: Boolean = false
    )

    fun loadCircularImageInto(url: String?, placeholderImageId: Int, targetImageView: ImageView)

    fun loadResizedImageInto(url: String?, placeholderImageId: Int, targetImageView: ImageView, w: Int, h: Int)

    fun loadBlurredImageInto(url: String?, placeholderImageId: Int, targetImageView: ImageView)

    fun loadImageFromDeviceStorage(
        uri: Uri,
        placeholderImageId: Int,
        targetImageView: ImageView,
        centerInside: Boolean = false,
        centerCrop: Boolean = false
    )
}