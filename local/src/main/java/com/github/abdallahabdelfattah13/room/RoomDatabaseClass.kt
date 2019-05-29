package com.github.abdallahabdelfattah13.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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


@Module
class RoomDatabaseModule() {

    @Provides
    @Singleton
    fun providesRoomDatabase(appContext: Context): RoomDatabaseClass = RoomDatabaseClass.getDatabase(appContext)
}