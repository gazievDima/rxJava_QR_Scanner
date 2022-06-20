package com.gaziev.qr_code_scanner.data.mapper

import com.gaziev.qr_code_scanner.data.model.ScanUrlEntity
import com.gaziev.qr_code_scanner.domain.model.ScanUrlDetails

class ScanUrlMapper : Mapper<ScanUrlEntity, ScanUrlDetails> {

    override fun mapFrom(t: ScanUrlEntity): ScanUrlDetails  {
        return ScanUrlDetails(
            title = t.title,
            url = t.url
        )
    }

    override fun mapTo(v: ScanUrlDetails): ScanUrlEntity {
        return ScanUrlEntity(
            title = v.title,
            url = v.url
        )
    }
}