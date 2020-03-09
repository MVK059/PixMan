package com.mvk.pixman.di.module

import android.app.Application
import android.content.Context
import com.mvk.pixman.PixManApp
import com.mvk.pixman.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: PixManApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application
}