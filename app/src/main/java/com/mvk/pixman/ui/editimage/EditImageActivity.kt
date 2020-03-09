package com.mvk.pixman.ui.editimage

import android.os.Bundle
import com.mvk.pixman.BR
import com.mvk.pixman.R
import com.mvk.pixman.databinding.ActivityEditImageBinding
import com.mvk.pixman.di.component.ActivityComponent
import com.mvk.pixman.ui.addimage.ImageViewModel
import com.mvk.pixman.ui.base.BaseActivity

class EditImageActivity : BaseActivity<ActivityEditImageBinding, ImageViewModel>() {

    /**
     * Set data binding for the activity
     *
     * @return View model from the layout file
     */
    override fun provideDataBindingVariable(): Int = BR.editVM

    /**
     * Set layout for the activity
     *
     * @return Layout file for the activity
     */
    override fun provideLayoutId(): Int = R.layout.activity_edit_image

    /**
     * Inject dependencies for the activity
     *
     * @param activityComponent Component for the activity
     */
    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    /**
     * Setup view for the activity
     */
    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        // TODO Show popup
    }

}
