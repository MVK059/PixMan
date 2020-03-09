package com.mvk.pixman.ui.addimage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mvk.pixman.BR
import com.mvk.pixman.R
import com.mvk.pixman.databinding.ActivityAddImageBinding
import com.mvk.pixman.di.component.ActivityComponent
import com.mvk.pixman.ui.base.BaseActivity
import com.mvk.pixman.utils.common.Constants
import com.mvk.pixman.utils.log.Logger
import java.io.FileNotFoundException


class AddImageActivity : BaseActivity<ActivityAddImageBinding, AddImageViewModel>() {
    /**
     * Set data binding for the activity
     *
     * @return View model from the layout file
     */
    override fun provideDataBindingVariable(): Int = BR.addVM

    /**
     * Set layout for the activity
     *
     * @return Layout file for the activity
     */
    override fun provideLayoutId(): Int = R.layout.activity_add_image

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

    override fun setupObservers() {
        super.setupObservers()

        viewModel.addImageClick.observe(this, Observer {
            Intent(Intent.ACTION_PICK)
                .apply { type = "image/*" }
                .run { startActivityForResult(this, Constants.RESULT_GALLERY_IMAGE) }
        })

        viewModel.launchEditImage.observe(this, Observer {
            it.getIfNotHandled()?.run {
                (this@AddImageActivity as BaseActivity<*, *>)
                    .navigationController.launchEditImageActivity(viewModel.imageURI)

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.RESULT_GALLERY_IMAGE -> {
                    try {
                        data?.data?.let {
                            contentResolver?.openInputStream(it)?.run {
                                viewModel.onGalleryImageSelected(it)
                            }
                        } ?: showMessage(R.string.add_image_try_again)
                    } catch (e: FileNotFoundException) {
                        Logger.e(Constants.ADD_IMAGE_ACTIVITY_TAG, e.printStackTrace().toString())
                        showMessage(R.string.add_image_try_again)
                    }
                }
            }
        }
    }
}
