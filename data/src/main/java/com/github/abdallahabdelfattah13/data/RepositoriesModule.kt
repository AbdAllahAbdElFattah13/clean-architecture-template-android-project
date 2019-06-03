package com.github.abdallahabdelfattah13.data

import com.github.abdallahabdelfattah13.remote.retrofit.services.ServicesModule
import com.github.abdallahabdelfattah13.room.dao.DoaModule
import dagger.Module


/**
 * Created by AbdAllah AbdElfattah on 03/06/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Module(
    includes = [
        DoaModule::class,
        ServicesModule::class
    ]
)
class RepositoriesModule {

    //region Add all of your repositories provide method here.
    //endregion

}