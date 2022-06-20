package com.gaziev.qr_code_scanner.core

import android.graphics.Bitmap
import com.gaziev.qr_code_scanner.presentation.screens.scanner.ScannerCode
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import io.reactivex.rxjava3.core.Single

class ScannerCodeMLKit : ScannerCode {

    override fun getUrlByBitmap(imageBitmap: Bitmap): Single<ScanUrlDetails> =
        Single.create { emmiter ->

            val image = InputImage.fromBitmap(imageBitmap, 0)
            val options = setupSettings()
            val scanner = BarcodeScanning.getClient(options)

            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.size > 0) {
                        for (barcode in barcodes) {
                            if (check(barcode))
                                emmiter.onSuccess(
                                    ScanUrlDetails(barcode.url?.title!!, barcode.url?.url!!)
                                )
                            else
                                emmiter.onError(Throwable())
                        }
                    } else {
                        emmiter.onError(Throwable())
                    }
                }
                .addOnFailureListener {
                    emmiter.onError(it)
                }


        }

    private fun check(barcode: Barcode): Boolean =
        barcode.valueType == Barcode.TYPE_URL &&
                barcode.url?.title != null &&
                barcode.url?.url != null

    private fun setupSettings(): BarcodeScannerOptions =
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC
            )
            .build()
}