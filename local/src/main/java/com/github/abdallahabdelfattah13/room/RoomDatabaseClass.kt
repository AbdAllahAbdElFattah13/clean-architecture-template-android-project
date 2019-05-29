package com.github.abdallahabdelfattah13.room

import android.content.Context
import androidx.room.*
import com.github.abdallahabdelfattah13.context.ContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by AbdAllah AbdElfattah on 27/05/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
@Database(
    version = 1,
    entities = [
        DummyEntityToPassBuildForNow::class
        //region add all of your models/entities here.
        //endregion
    ]
)
abstract class RoomDatabaseClass : RoomDatabase() {

    //region define all of your dao getters here.
    //endregion

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabaseClass? = null

        fun getDatabase(appContext: Context): RoomDatabaseClass {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext.applicationContext,
                    RoomDatabaseClass::class.java,
                    "Main_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

@Entity
data class DummyEntityToPassBuildForNow(@PrimaryKey val dummyPrimaryKeyToPassBuildForNow: String)

@Module(
    includes = [
        ContextModule::class
    ]
)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(appContext: Context): RoomDatabaseClass = RoomDatabaseClass.getDatabase(appContext)
}