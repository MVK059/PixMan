package com.mvk.pixman.di.module

import androidx.lifecycle.ViewModelProvider
import com.mvk.pixman.ui.addimage.AddImageViewModel
import com.mvk.pixman.ui.base.BaseActivity
import com.mvk.pixman.ui.editimage.EditImageViewModel
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
    fun provideImageViewModel(): AddImageViewModel =
        ViewModelProvider(
            activity, ViewModelProviderFactory(AddImageViewModel::class) {
                AddImageViewModel()
            }).get(AddImageViewModel::class.java)

    @Provides
    fun provideEditImageViewModel(): EditImageViewModel =
        ViewModelProvider(
            activity, ViewModelProviderFactory(EditImageViewModel::class) {
                EditImageViewModel()
            }).get(EditImageViewModel::class.java)
}