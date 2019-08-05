package io.thed.device.usecases.upload_file_usecase.usecase

import android.content.Context
import android.util.Log
import net.gotev.uploadservice.*


/**
 * Created by AbdAllah AbdElfattah on 03/07/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class UploadFileUseCase {

    private val TAG = "UploadFileUseCase"

    fun run(
        context: Context,
        url: String,
        filePath: String,
        paramName: String
    ) {

        try {
            val uploadId = MultipartUploadRequest(context, url)
                .addFileToUpload(filePath, paramName)
                .setNotificationConfig(UploadNotificationConfig())
                .setMaxRetries(2)
                .setMethod("PUT")

                //AbdAllah: I'm leaving setDelegate calls though it's only
                //print logs for now, to avoid the broadcasting,
                //also it will be helpful in a later stage to convert
                //it to an observable.
                .setDelegate(object : UploadStatusDelegate {
                    override fun onCancelled(context: Context?, uploadInfo: UploadInfo?) {
                        Log.d(TAG, "onCancelled: $uploadInfo")

                    }

                    override fun onProgress(context: Context?, uploadInfo: UploadInfo?) {
                        Log.d(TAG, "onProgress: $uploadInfo")
                    }

                    override fun onError(
                        context: Context?,
                        uploadInfo: UploadInfo?,
                        serverResponse: ServerResponse?,
                        exception: java.lang.Exception?
                    ) {
                        Log.d(TAG, "onError: $uploadInfo")
                    }

                    override fun onCompleted(
                        context: Context?,
                        uploadInfo: UploadInfo?,
                        serverResponse: ServerResponse?
                    ) {
                        Log.d(TAG, "onCompleted: $uploadInfo")
                    }

                })

                .startUpload()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }


}