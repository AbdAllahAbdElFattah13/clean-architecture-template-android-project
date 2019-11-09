package com.github.abdallahabdelfattah13.clean_architecture_template_project.Schedulers

import com.github.abdallahabdelfattah13.domain.qualifires.Background
import com.github.abdallahabdelfattah13.domain.qualifires.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 09,November,2019
 * Cairo, Egypt.
 */
@Module
class SchedulersModule {

    @Singleton
    @Provides
    @Background
    fun providesBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Singleton
    @Provides
    @Foreground
    fun providesForegroundScheduler(): Scheduler {
        return  AndroidSchedulers.mainThread() }
}