package com.mvk.pixman.ui.addimage

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mvk.pixman.ui.base.BaseViewModel
import com.mvk.pixman.utils.common.Event
import java.io.InputStream

class ImageViewModel : BaseViewModel() {

    lateinit var imageInputString: InputStream
    val addImageClick = MutableLiveData<Event<Map<String, String>>>()

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchEditImage: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {

    }

    fun onGalleryImageSelected(inputStream: InputStream) {
        imageInputString = inputStream
        launchEditImage.postValue(Event(emptyMap()))
    }

    fun onAddClick(view: View) {
        addImageClick.postValue(Event(emptyMap()))
    }

    fun onFlipHorizontalClick(view: View) {

    }

    fun onFlipVerticalClick(view: View) {

    }

    fun onSetOpacityClick(view: View) {

    }

    fun onAddTextClick(view: View) {

    }

    fun onSaveImageClick(view: View) {

    }


}