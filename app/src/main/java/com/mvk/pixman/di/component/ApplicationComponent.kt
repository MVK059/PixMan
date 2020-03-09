package com.mvk.pixman.di.component

import android.app.Application
import android.content.Context
import com.mvk.pixman.PixManApp
import com.mvk.pixman.di.ApplicationContext
import com.mvk.pixman.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: PixManApp)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context
}