package com.mvk.pixman.di.component

import com.mvk.pixman.di.ActivityScope
import com.mvk.pixman.di.module.ActivityModule
import com.mvk.pixman.ui.addimage.AddImageActivity
import com.mvk.pixman.ui.editimage.EditImageActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: AddImageActivity)

    fun inject(activity: EditImageActivity)
}