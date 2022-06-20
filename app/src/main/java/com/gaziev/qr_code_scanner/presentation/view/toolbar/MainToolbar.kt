package com.gaziev.qr_code_scanner.presentation.view.toolbar

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class MainToolbar(
    private val toolbar: Toolbar,
    navController: NavController
) {

    init {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    fun setup(fragment: Fragment) {
        if (fragment is ToolbarView) show(fragment)
        else hide()
    }

    private fun show(fragment: ToolbarView) {
        toolbar.visibility = View.VISIBLE
        toolbar.menu[0].isVisible = fragment is ToolbarViewHistory
    }

    private fun hide() {
        toolbar.visibility = View.GONE
    }

}