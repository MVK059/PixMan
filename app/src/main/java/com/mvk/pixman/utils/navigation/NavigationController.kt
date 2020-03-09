package com.mvk.pixman.utils.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mvk.pixman.ui.editimage.EditImageActivity
import com.mvk.pixman.utils.common.Constants
import javax.inject.Inject

class NavigationController @Inject constructor(
    var context: Context
) {

    /**
     * Launch MainActivity
     */
    fun launchEditImageActivity(uri: Uri) {
        val intent = Intent(context, EditImageActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(Constants.BUNDLE_EXTRA, uri.toString())
        (context).startActivity(intent)
    }
}