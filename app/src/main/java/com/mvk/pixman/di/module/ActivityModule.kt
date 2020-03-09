package com.mvk.pixman.di.module

import androidx.lifecycle.ViewModelProvider
import com.mvk.pixman.ui.addimage.ImageViewModel
import com.mvk.pixman.ui.base.BaseActivity
import com.mvk.pixman.utils.ViewModelProviderFactory
import com.mvk.pixman.utils.navigation.NavigationController
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideNavigationController(): NavigationController =
        NavigationController(activity)

    @Provides
    fun provideImageViewModel(): ImageViewModel =
        ViewModelProvider(
            activity, ViewModelProviderFactory(ImageViewModel::class) {
                ImageViewModel()
            }).get(ImageViewModel::class.java)
}