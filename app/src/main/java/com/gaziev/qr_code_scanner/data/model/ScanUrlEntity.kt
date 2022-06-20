package com.gaziev.qr_code_scanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ScanUrlEntity(
    @PrimaryKey(autoGenerate = true) var primary_key: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String
)