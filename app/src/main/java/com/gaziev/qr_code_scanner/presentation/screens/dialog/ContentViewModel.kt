package com.gaziev.qr_code_scanner.presentation.screens.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails

class ContentViewModel : ViewModel() {

    private var _scanUrlModel: MutableLiveData<ScanUrlDetails> = MutableLiveData()
    val scanUrlModel: LiveData<ScanUrlDetails> = _scanUrlModel

    fun setScanUrlModel(model: ScanUrlDetails) {
        _scanUrlModel.value = model
    }
}