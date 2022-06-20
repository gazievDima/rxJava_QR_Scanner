package com.gaziev.qr_code_scanner.presentation.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaziev.qr_code_scanner.App
import com.gaziev.qr_code_scanner.core.ScannerCodeMLKit
import com.gaziev.qr_code_scanner.data.mapper.Mapper
import com.gaziev.qr_code_scanner.data.mapper.ScanUrlMapper
import com.gaziev.qr_code_scanner.data.model.ScanUrlEntity
import com.gaziev.qr_code_scanner.data.repository.ScanUrlRepositoryImpl
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import com.gaziev.qr_code_scanner.presentation.repository.ScanUrlRepository
import com.gaziev.qr_code_scanner.presentation.screens.dialog.ContentViewModel
import com.gaziev.qr_code_scanner.presentation.screens.history.HistoryViewModel
import com.gaziev.qr_code_scanner.presentation.screens.scanner.ScannerViewModel
import com.gaziev.qr_code_scanner.presentation.screens.scanner.ScannerCode

class MainViewModelFactory() : ViewModelProvider.Factory {

    private val mapper: Mapper<ScanUrlEntity, ScanUrlDetails> = ScanUrlMapper()
    private val scanUrlDao = App.instance.scanUrlDao
    private val scanUrlRepository: ScanUrlRepository = ScanUrlRepositoryImpl(scanUrlDao, mapper)
    private val scannerCodeMLKit: ScannerCode = ScannerCodeMLKit()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HistoryViewModel::class.java) ->
                HistoryViewModel(scanUrlRepository) as T
            modelClass.isAssignableFrom(ScannerViewModel::class.java) ->
                ScannerViewModel(scanUrlRepository, scannerCodeMLKit) as T
            modelClass.isAssignableFrom(ContentViewModel::class.java) ->
                ContentViewModel() as T
            else -> modelClass.newInstance()
        }
    }
}