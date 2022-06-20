package com.gaziev.qr_code_scanner

import android.app.Application
import androidx.room.Room
import com.gaziev.qr_code_scanner.data.source.local.RoomLocalDataBase
import com.gaziev.qr_code_scanner.data.source.local.ScanUrlDao

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val database: RoomLocalDataBase by lazy {
        Room.databaseBuilder(
            applicationContext,
            RoomLocalDataBase::class.java, "database_common"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    val scanUrlDao: ScanUrlDao by lazy { database.getScanUrlDao() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}