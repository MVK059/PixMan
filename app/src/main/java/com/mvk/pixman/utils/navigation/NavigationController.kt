package com.mvk.pixman.utils.navigation

import android.content.Context
import javax.inject.Inject

class NavigationController @Inject constructor(
    var context: Context
) {

    /**
     * Launch MainActivity
     */
    fun launchMainActivity() {
//        val intent =
//            Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        (context).startActivity(intent)
    }
}