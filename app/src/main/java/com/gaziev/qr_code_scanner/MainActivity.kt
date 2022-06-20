package com.gaziev.qr_code_scanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gaziev.qr_code_scanner.databinding.ActivityMainBinding
import com.gaziev.qr_code_scanner.presentation.view.toolbar.MainToolbar

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
    }
    val toolbar: Toolbar by lazy { binding.toolbar }
    val mainToolbar: MainToolbar by lazy { MainToolbar(toolbar, navController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()) finish()
    }

}
