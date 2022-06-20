package com.gaziev.qr_code_scanner.presentation.screens.scanner

import android.graphics.Bitmap
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import io.reactivex.rxjava3.core.Single

interface ScannerCode {
    fun getUrlByBitmap(imageBitmap: Bitmap): Single<ScanUrlDetails>
}