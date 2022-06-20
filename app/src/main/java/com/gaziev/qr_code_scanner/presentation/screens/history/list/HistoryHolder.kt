package com.gaziev.qr_code_scanner.presentation.screens.history.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gaziev.qr_code_scanner.databinding.ItemHistoryBinding
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails

class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemHistoryBinding by lazy { ItemHistoryBinding.bind(itemView) }

    fun bind(item: ScanUrlDetails, onClick: (itemChoose: ScanUrlDetails) -> Unit) {
        binding.tvUrl.apply {
            text = item.url
            setOnClickListener { onClick(item) }
        }
    }
}