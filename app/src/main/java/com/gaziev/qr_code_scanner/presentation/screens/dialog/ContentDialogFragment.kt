package com.gaziev.qr_code_scanner.presentation.screens.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.gaziev.qr_code_scanner.R
import com.gaziev.qr_code_scanner.databinding.DialogContentBinding
import com.gaziev.qr_code_scanner.presentation.common.MainViewModelFactory

class ContentDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "ContentDialogFragment"
    }
    private val viewModel: ContentViewModel by lazy {
        ViewModelProvider(requireActivity(), MainViewModelFactory())[ContentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DialogContentBinding.bind(view)

        viewModel.scanUrlModel.observe(viewLifecycleOwner) { scanUrlModel ->
            binding.tvContent.text = scanUrlModel.url

            binding.btnGo.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(scanUrlModel.url)))
            }
            binding.btnCancel.setOnClickListener {
                this.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

}