package com.gaziev.qr_code_scanner.presentation.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gaziev.qr_code_scanner.R
import com.gaziev.qr_code_scanner.databinding.FrStartBinding
import com.gaziev.qr_code_scanner.presentation.common.BaseFragment
import com.gaziev.qr_code_scanner.presentation.view.toolbar.ToolbarView

class StartFragment : BaseFragment<FrStartBinding>(), ToolbarView  {
    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FrStartBinding =
        FrStartBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnScanner.setOnClickListener {
                findNavController().navigate(R.id.scannerFragment)
            }
            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
            btnExit.setOnClickListener {
                requireActivity().finish()
            }
        }
    }

}