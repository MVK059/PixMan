package com.mvk.pixman.ui.editimage

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mvk.pixman.BR
import com.mvk.pixman.R
import com.mvk.pixman.databinding.ActivityEditImageBinding
import com.mvk.pixman.di.component.ActivityComponent
import com.mvk.pixman.ui.base.BaseActivity
import com.mvk.pixman.utils.common.Constants

class EditImageActivity : BaseActivity<ActivityEditImageBinding, EditImageViewModel>() {
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
        setupImage()
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.permissionCheck.observe(this, Observer {
            viewModel.checkPermission(this)
        })

        viewModel.finishOperation.observe(this, Observer {
            finish()
        })
    }

    private fun setupImage() {
        val intentImageUri = intent.getStringExtra(Constants.BUNDLE_EXTRA)
        val imageUri = Uri.parse(intentImageUri)
        dataBinding.editImageMainIV.setImageURI(imageUri)
        setProperties(imageUri)
    }

    private fun setProperties(imageUri: Uri) {
        viewModel.mainImageView = dataBinding.editImageMainIV
        viewModel.setImageBitmap(imageUri, contentResolver)
        viewModel.imageViewLayout = dataBinding.editImageLayout
    }

}
