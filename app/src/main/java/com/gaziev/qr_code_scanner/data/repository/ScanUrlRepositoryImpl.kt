package com.gaziev.qr_code_scanner.data.repository

import com.gaziev.qr_code_scanner.data.mapper.Mapper
import com.gaziev.qr_code_scanner.data.model.ScanUrlEntity
import com.gaziev.qr_code_scanner.data.source.local.ScanUrlDao
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails
import com.gaziev.qr_code_scanner.presentation.repository.ScanUrlRepository
import io.reactivex.rxjava3.core.Single

class ScanUrlRepositoryImpl(
    private val scanUrlDao: ScanUrlDao,
    private val mapper: Mapper<ScanUrlEntity, ScanUrlDetails>
) : ScanUrlRepository {

    override fun getAllScanUrl(): Single<List<ScanUrlDetails>> {
        return scanUrlDao.getAll()
            .flatMap { list ->
                Single.create<List<ScanUrlDetails>> {
                    it.onSuccess(list.map { mapper.mapFrom(it) })
                }
            }
    }

    override fun setOneScanUrl(el: ScanUrlDetails) {
        scanUrlDao.setOne(mapper.mapTo(el))
    }

   override fun deleteAllScanUrlItems() {
        scanUrlDao.deleteAll()
   }

}