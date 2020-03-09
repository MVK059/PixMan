package com.mvk.pixman.utils.navigation

import android.content.Context
import android.content.Intent
import com.mvk.pixman.ui.editimage.EditImageActivity
import javax.inject.Inject

class NavigationController @Inject constructor(
    var context: Context
) {

    /**
     * Launch MainActivity
     */
    fun launchEditImageActivity() {
        val intent = Intent(context, EditImageActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        (context).startActivity(intent)
    }
}