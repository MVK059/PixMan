package com.mvk.pixman.ui.editimage

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.mvk.pixman.ui.base.BaseViewModel
import com.mvk.pixman.utils.common.Constants
import com.mvk.pixman.utils.display.Toaster


class EditImageViewModel : BaseViewModel() {

    lateinit var imageBitmap: Bitmap
    lateinit var mainImageView: ImageView

    override fun onCreate() {

    }

    fun setImageBitmap(imageUri: Uri, contentResolver: ContentResolver) {
        contentResolver.openInputStream(imageUri)?.run {
            imageBitmap = BitmapFactory.decodeStream(this)
        }
        Toaster.show(mainImageView.context, "${mainImageView.rotationX} ${mainImageView.rotationY}")
    }

    fun onFlipHorizontalClick(view: View) {
        if (mainImageView.rotationY == 0f)
            mainImageView.rotationY = 180f
        else
            mainImageView.rotationY = 0f
        Toaster.show(mainImageView.context, "${mainImageView.rotationX} ${mainImageView.rotationY}")
    }

    fun onFlipVerticalClick(view: View) {
        if (mainImageView.rotationX == 0f)
            mainImageView.rotationX = 180f
        else
            mainImageView.rotationX = 0f
        Toaster.show(mainImageView.context, "${mainImageView.rotationX} ${mainImageView.rotationY}")
    }

    fun onSetOpacityClick(view: View) {
        mainImageView.imageAlpha = 128
    }

    fun onAddTextClick(view: View) {

    }

    fun onSaveImageClick(view: View) {

    }
}