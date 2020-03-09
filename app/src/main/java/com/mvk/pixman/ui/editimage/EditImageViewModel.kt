package com.mvk.pixman.ui.editimage

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.mvk.pixman.R
import com.mvk.pixman.ui.base.BaseViewModel


class EditImageViewModel : BaseViewModel() {

    lateinit var imageBitmap: Bitmap
    lateinit var mainImageView: ImageView
    lateinit var imageViewLayout: ConstraintLayout

    override fun onCreate() {

    }

    fun setImageBitmap(imageUri: Uri, contentResolver: ContentResolver) {
        contentResolver.openInputStream(imageUri)?.run {
            imageBitmap = BitmapFactory.decodeStream(this)
        }
    }

    fun onFlipHorizontalClick(view: View) {
        if (mainImageView.rotationY == 0f)
            mainImageView.rotationY = 180f
        else
            mainImageView.rotationY = 0f
    }

    fun onFlipVerticalClick(view: View) {
        if (mainImageView.rotationX == 0f)
            mainImageView.rotationX = 180f
        else
            mainImageView.rotationX = 0f
    }

    fun onSetOpacityClick(view: View) {
        mainImageView.imageAlpha = 128
    }

    fun onAddTextClick(view: View) {
        val set = ConstraintSet()
        val textView = TextView(mainImageView.context)
        textView.id = View.generateViewId()
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.text = mainImageView.context.getString(R.string.edit_image_greedy_game)
        textView.setTextColor(ContextCompat.getColor(mainImageView.context, R.color.green))
        textView.setBackgroundColor(ContextCompat.getColor(mainImageView.context, R.color.black))
        imageViewLayout.addView(textView)

        set.clone(imageViewLayout)
        set.connect(textView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(textView.id, ConstraintSet.BOTTOM, mainImageView.id, ConstraintSet.BOTTOM)
        set.applyTo(imageViewLayout)
    }

    fun onSaveImageClick(view: View) {

    }
}