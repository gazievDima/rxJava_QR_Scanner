package com.gaziev.qr_code_scanner.presentation.screens.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaziev.qr_code_scanner.R
import com.gaziev.qr_code_scanner.databinding.FrHistoryBinding
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import com.gaziev.qr_code_scanner.presentation.common.BaseFragment
import com.gaziev.qr_code_scanner.presentation.common.MainViewModelFactory
import com.gaziev.qr_code_scanner.presentation.screens.dialog.ContentDialogFragment
import com.gaziev.qr_code_scanner.presentation.screens.dialog.ContentViewModel
import com.gaziev.qr_code_scanner.presentation.screens.history.list.HistoryAdapter
import com.gaziev.qr_code_scanner.presentation.view.toolbar.ToolbarView
import com.gaziev.qr_code_scanner.presentation.view.toolbar.ToolbarViewHistory

class HistoryFragment : BaseFragment<FrHistoryBinding>(), ToolbarView, ToolbarViewHistory {
    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FrHistoryBinding =
        FrHistoryBinding::inflate
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory())[HistoryViewModel::class.java]
    }
    private val viewModelContent: ContentViewModel by lazy {
        ViewModelProvider(requireActivity(), MainViewModelFactory())[ContentViewModel::class.java]
    }
    private var list: MutableList<ScanUrlDetails> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarListener()
        observeHistories()
        viewModel.getHistories { errorMsg -> snackbar(errorMsg) }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun toolbarListener() {
        mainActivity.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.deleteAll -> {
                    viewModel.clearAllHistories()
                    list.clear()
                    binding.recycler.adapter?.notifyDataSetChanged()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun observeHistories() {
        viewModel.histories.observe(viewLifecycleOwner) {
            binding.recycler.apply {
                list = it.toMutableList()

                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = HistoryAdapter(list) { item: ScanUrlDetails ->
                    viewModelContent.setScanUrlModel(item)
                    ContentDialogFragment().show(parentFragmentManager, ContentDialogFragment.TAG)
                }
            }
        }
    }

}