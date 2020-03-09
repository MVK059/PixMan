package com.mvk.pixman.ui.addimage

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mvk.pixman.ui.base.BaseViewModel
import com.mvk.pixman.utils.common.Event
import java.io.InputStream

class ImageViewModel : BaseViewModel() {

    lateinit var imageInputString: InputStream
    val addImageClick = MutableLiveData<Event<Map<String, String>>>()
    val selectImage = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {

    }

    fun onGalleryImageSelected(inputStream: InputStream) {
        imageInputString = inputStream
        selectImage.postValue(Event(true))
    }

    fun onAddClick(view: View) {
        addImageClick.postValue(Event(emptyMap()))

    }
}