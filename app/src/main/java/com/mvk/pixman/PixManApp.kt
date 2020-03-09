package com.mvk.pixman

import android.app.Application
import com.mvk.pixman.di.component.ApplicationComponent
import com.mvk.pixman.di.component.DaggerApplicationComponent
import com.mvk.pixman.di.module.ApplicationModule

class PixManApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}