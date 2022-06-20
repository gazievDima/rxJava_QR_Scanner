package com.gaziev.qr_code_scanner.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaziev.qr_code_scanner.data.model.ScanUrlEntity

@Database(entities = [ScanUrlEntity::class], version = 2)
abstract class RoomLocalDataBase : RoomDatabase() {
    abstract fun getScanUrlDao(): ScanUrlDao
}