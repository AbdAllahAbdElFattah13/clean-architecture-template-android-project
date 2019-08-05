package io.thed.device.usecases

import dagger.Module
import dagger.Provides
import io.thed.device.usecases.read_file_from_gallery_usecase.usecase.ReadFileFromGalleryUseCase
import io.thed.device.usecases.upload_file_usecase.usecase.UploadFileUseCase
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 03/07/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module
class DeviceUseCasesModule {

    @Provides
    @Singleton
    fun providesReadFileFromGalleryUseCase() = ReadFileFromGalleryUseCase()

    @Provides
    @Singleton
    fun providesUploadFileUseCase() = UploadFileUseCase()

}