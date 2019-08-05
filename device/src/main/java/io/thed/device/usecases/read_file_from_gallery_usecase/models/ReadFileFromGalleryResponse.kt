package io.thed.device.usecases.read_file_from_gallery_usecase.models

import java.io.File


/**
 * Created by AbdAllah AbdElfattah on 03/07/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
sealed class ReadFileFromGalleryResponse {

    class Success(val file: File) : ReadFileFromGalleryResponse()
    object Fail : ReadFileFromGalleryResponse()

}