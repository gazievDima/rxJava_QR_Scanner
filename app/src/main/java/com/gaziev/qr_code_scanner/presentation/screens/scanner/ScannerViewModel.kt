package com.gaziev.qr_code_scanner.presentation.screens.scanner

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import com.gaziev.qr_code_scanner.presentation.repository.ScanUrlRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ScannerViewModel(
    private val scanUrlRepository: ScanUrlRepository,
    private val scannerCode: ScannerCode
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    private var _scanUrlModel: MutableLiveData<ScanUrlDetails> = MutableLiveData()
    val scanUrlModel: LiveData<ScanUrlDetails> = _scanUrlModel

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun scan(image: Bitmap, showError: () -> Unit) {
        Log.e(TAG, "scan")
        compositeDisposable.add(
            scannerCode.getUrlByBitmap(image)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ scanModel ->
                    Log.e(TAG, "vm: success")
                    _scanUrlModel.value = scanModel
                }, { error ->
                    Log.e(TAG, "vm: error")
                    showError()
                })
        )
    }

    fun saveHistory(el: ScanUrlDetails) {
        compositeDisposable.add(
            Schedulers.io().createWorker()
                .schedule {
                    scanUrlRepository.setOneScanUrl(el)
                }
        )
    }

}