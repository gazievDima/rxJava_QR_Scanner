package com.gaziev.qr_code_scanner.presentation.screens.scanner

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.gaziev.qr_code_scanner.R
import com.gaziev.qr_code_scanner.databinding.FrScannerBinding
import com.gaziev.qr_code_scanner.presentation.common.BaseFragment
import com.gaziev.qr_code_scanner.presentation.common.MainViewModelFactory
import com.gaziev.qr_code_scanner.presentation.screens.dialog.ContentDialogFragment
import com.gaziev.qr_code_scanner.presentation.screens.dialog.ContentViewModel
import com.gaziev.qr_code_scanner.presentation.view.toolbar.ToolbarView

class ScannerFragment : BaseFragment<FrScannerBinding>(), ToolbarView {
    companion object {
        const val REQUEST_IMAGE_CAPTURE = 777
    }

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FrScannerBinding =
        FrScannerBinding::inflate
    private val viewModel: ScannerViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory())[ScannerViewModel::class.java]
    }
    private val viewModelContent: ContentViewModel by lazy {
        ViewModelProvider(requireActivity(), MainViewModelFactory())[ContentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding()
        observed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            viewModel.scan(imageBitmap) {
                snackbar(getString(R.string.fail_recognize_scan_code))
            }
        }
    }

    private fun viewBinding() {
        for (view in binding.scannerContainer.children) {
            view.setOnClickListener {
                dispatchTakePictureIntent()
            }
        }
    }

    private fun observed() {
        viewModel.scanUrlModel.observe(viewLifecycleOwner) {
            viewModel.saveHistory(it)
            viewModelContent.setScanUrlModel(it)
            ContentDialogFragment().show(parentFragmentManager, ContentDialogFragment.TAG)
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            snackbar(getString(R.string.failed_getting_photo))
        }
    }

}








