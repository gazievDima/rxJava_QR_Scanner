package com.gaziev.qr_code_scanner.presentation.screens.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import com.gaziev.qr_code_scanner.presentation.repository.ScanUrlRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel(
    private val scanUrlRepository: ScanUrlRepository
) : ViewModel() {

    private var _histories: MutableLiveData<List<ScanUrlDetails>> = MutableLiveData()
    val histories: LiveData<List<ScanUrlDetails>> = _histories
    private var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getHistories(snackbar: (errorMsg: String) -> Unit) {
        compositeDisposable.add(
            scanUrlRepository.getAllScanUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    _histories.postValue(list.reversed())
                }, { error ->
                    snackbar("Data could not be retrieved..")
                })
        )
    }

    fun clearAllHistories() {
        compositeDisposable.add(
            Schedulers.io().createWorker()
                .schedule {
                    scanUrlRepository.deleteAllScanUrlItems()
                }
        )
    }

}