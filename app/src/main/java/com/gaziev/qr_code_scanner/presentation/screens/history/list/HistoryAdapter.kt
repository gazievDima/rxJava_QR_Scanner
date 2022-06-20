package com.gaziev.qr_code_scanner.presentation.screens.history.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaziev.qr_code_scanner.R
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails

class HistoryAdapter(
    private val histories: List<ScanUrlDetails>,
    private val onClick: (itemChoose: ScanUrlDetails) -> Unit
) : RecyclerView.Adapter<HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) =
        holder.bind(histories[position], onClick)
    override fun getItemCount(): Int = histories.size
}