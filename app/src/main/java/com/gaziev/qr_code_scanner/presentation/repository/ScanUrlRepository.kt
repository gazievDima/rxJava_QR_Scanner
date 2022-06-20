package com.gaziev.qr_code_scanner.presentation.repository

import com.gaziev.qr_code_scanner.data.model.ScanUrlEntity
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import io.reactivex.rxjava3.core.Single

interface ScanUrlRepository {
    fun getAllScanUrl(): Single<List<ScanUrlDetails>>
    fun setOneScanUrl(el: ScanUrlDetails)
    fun deleteAllScanUrlItems()
}