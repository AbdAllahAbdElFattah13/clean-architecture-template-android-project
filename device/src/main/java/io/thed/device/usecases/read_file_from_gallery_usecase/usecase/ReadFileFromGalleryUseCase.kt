package io.thed.device.usecases.read_file_from_gallery_usecase.usecase

import android.app.Activity.RESULT_OK
import androidx.fragment.app.Fragment
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import io.reactivex.Observable
import io.thed.device.usecases.read_file_from_gallery_usecase.models.ReadFileFromGalleryResponse


/**
 * Created by AbdAllah AbdElfattah on 03/07/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
class ReadFileFromGalleryUseCase {

    fun run(fragment: Fragment): Observable<ReadFileFromGalleryResponse> {
        return RxPaparazzo
            .single(fragment)
            .usingGallery()
            .map { response ->
                if (response.resultCode() == RESULT_OK)
                    ReadFileFromGalleryResponse.Success(file = response.data().file)
                else
                    ReadFileFromGalleryResponse.Fail
            }
    }


}