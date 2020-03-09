package com.mvk.pixman.ui.editimage

import android.Manifest
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mvk.pixman.R
import com.mvk.pixman.ui.base.BaseViewModel
import com.mvk.pixman.utils.common.Event
import com.mvk.pixman.utils.display.Toaster
import java.io.File
import java.io.FileOutputStream


class EditImageViewModel : BaseViewModel() {

    lateinit var imageBitmap: Bitmap
    lateinit var mainImageView: ImageView
    lateinit var imageViewLayout: ConstraintLayout
    val permissionCheck = MutableLiveData<Event<Boolean>>()
    val finishOperation = MutableLiveData<Event<Boolean>>()

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
        permissionCheck.postValue(Event(true))
    }

    fun checkPermission(activity: EditImageActivity) {
        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report?.areAllPermissionsGranted() == true)
                        writeFileToStorage()
                    else
                        Toaster.show(activity, activity.getString(R.string.add_image_try_again))
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }).check()
    }

    private fun writeFileToStorage() {
        val bitmap = getBitmapFromView(imageViewLayout)
        val extStorage: File? = mainImageView.context.getExternalFilesDir(null)
        val directory = File(extStorage?.absolutePath + "/PixMan")
        directory.mkdirs()
        val fileName = String.format("%d.jpg", System.currentTimeMillis())
        val output = File(directory, fileName)
        val fileOutputStream = FileOutputStream(output)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
        Toaster.show(mainImageView.context, "File saved successfully to $directory")
        finishOperation.postValue(Event(true))
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}