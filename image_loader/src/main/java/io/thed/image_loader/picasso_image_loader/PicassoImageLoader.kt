package io.thed.image_loader.picasso_image_loader

import android.graphics.*
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation
import io.thed.image_loader.ImageLoader
import io.thed.image_loader.R


/**
 * Created by AbdAllah AbdElfattah on 02/08/2018,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class PicassoImageLoader constructor(
    private val picasso: Picasso,
    private val baseUrl: String
) : ImageLoader {

    //AbdAllah: this is quite fine for now, as I'll be using this
    //only for user images for now.
    private val defaultImageLoaderPlaceholder = R.drawable.user_image_placeholder
    private val defaultImageLoaderError = R.drawable.user_image_placeholder

    private fun convertEmptyStringsToNull(url: String?) = if (url?.isBlank() == true) null else url

    private fun constructTheImageUrl(nonEmptyUrl: String?): String? {
//        val imageToken = "?x-access-token=" + localDataSource.getUserToken()

        //if we found the 'http://' or 'https://' suffix,
        //then we'll consider this url as an absolute url, then
        //it's external url.
        //we need to know this so we will be able to decide
        //if we'll add the image token or not.
        val hasHttpProtocolSuffix =
            (nonEmptyUrl != null && (nonEmptyUrl.indexOf("http://", 0, true) > -1 || (nonEmptyUrl.indexOf(
                "https://",
                0,
                true
            ) > -1)))
        val isInternalUrl = !hasHttpProtocolSuffix

        return when {
            nonEmptyUrl == null -> null
            isInternalUrl -> baseUrl + nonEmptyUrl
            else -> nonEmptyUrl
        }
    }

    private fun createImageRequest(
        uri: Uri?,
        placeholderImageId: Int = defaultImageLoaderPlaceholder, errorImageId: Int = defaultImageLoaderError,
        targetImageView: ImageView,
        fit: Boolean = false, centerInside: Boolean = false, centerCrop: Boolean = false,
        isCircular: Boolean = false,
        w: Int = 0, h: Int = 0
    ): RequestCreator {

        if (centerInside && centerCrop) throw IllegalArgumentException("Both centerInside and centerCrop can't be true at the same time!")

        var picassoRequestCreator = picasso.load(uri).placeholder(placeholderImageId).error(errorImageId)

        if (fit) picassoRequestCreator = picassoRequestCreator.fit()
        if (centerInside) picassoRequestCreator = picassoRequestCreator.centerInside()
        if (centerCrop) picassoRequestCreator = picassoRequestCreator.centerCrop()

        if (isCircular) picassoRequestCreator = picassoRequestCreator.transform(CircleTransform())
        if (w > 0 && h > 0) picassoRequestCreator = picassoRequestCreator.resize(w, h)

        return picassoRequestCreator
    }

    private fun loadImageIntoImageView(requestCreator: RequestCreator, targetImageView: ImageView) {
        requestCreator.into(targetImageView)
    }

    private fun String?.convertToReadyToBeCalledByUri(): Uri? {
        val nonEmptyUri = convertEmptyStringsToNull(this)
        return if (nonEmptyUri == null) null
        else
            Uri.parse(
                constructTheImageUrl(nonEmptyUri)
            )
    }

    override fun loadImageInto(url: String?, targetImageView: ImageView) =
        loadImageInto(url, defaultImageLoaderPlaceholder, targetImageView)

    override fun loadImageInto(
        url: String?,
        placeholderImageId: Int,
        targetImageView: ImageView,
        centerInside: Boolean,
        centerCrop: Boolean
    ) =
        loadImageIntoImageView(
            createImageRequest(
                uri = url.convertToReadyToBeCalledByUri(),
                placeholderImageId = placeholderImageId,
                errorImageId = placeholderImageId,
                targetImageView = targetImageView,
                fit = centerCrop || centerInside,
                centerInside = centerInside,
                centerCrop = centerCrop
            ),
            targetImageView
        )

    override fun loadCircularImageInto(url: String?, placeholderImageId: Int, targetImageView: ImageView) =
        loadImageIntoImageView(
            createImageRequest(
                uri = url.convertToReadyToBeCalledByUri(),
                targetImageView = targetImageView,
                placeholderImageId = placeholderImageId,
                errorImageId = placeholderImageId,
                isCircular = true
            ),
            targetImageView
        )


    override fun loadResizedImageInto(
        url: String?,
        placeholderImageId: Int,
        targetImageView: ImageView,
        w: Int,
        h: Int
    ) =
        loadImageIntoImageView(
            createImageRequest(
                uri = url.convertToReadyToBeCalledByUri(),
                placeholderImageId = placeholderImageId,
                errorImageId = placeholderImageId,
                targetImageView = targetImageView,
                w = w, h = h
            ),
            targetImageView
        )

    override fun loadBlurredImageInto(url: String?, placeholderImageId: Int, targetImageView: ImageView) =
        loadImageIntoImageView(
            createImageRequest(
                uri = url.convertToReadyToBeCalledByUri(),
                placeholderImageId = placeholderImageId,
                errorImageId = placeholderImageId,
                targetImageView = targetImageView
            ),
            targetImageView
        )

    override fun loadImageFromDeviceStorage(
        uri: Uri,
        placeholderImageId: Int,
        targetImageView: ImageView,
        centerInside: Boolean,
        centerCrop: Boolean
    ) =
        loadImageIntoImageView(
            createImageRequest(
                uri = uri,
                placeholderImageId = placeholderImageId,
                errorImageId = placeholderImageId,
                targetImageView = targetImageView,
                centerCrop = centerCrop,
                centerInside = centerInside,
                fit = centerCrop || centerInside
            ),
            targetImageView
        )

}

class CircleTransform : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}
