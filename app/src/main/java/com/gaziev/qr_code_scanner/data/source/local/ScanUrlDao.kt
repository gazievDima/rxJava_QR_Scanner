package com.gaziev.qr_code_scanner.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gaziev.qr_code_scanner.data.model.ScanUrlEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface ScanUrlDao {

    @Query("SELECT * FROM scanurlentity")
    fun getAll(): Single<List<ScanUrlEntity>>

    @Insert
    fun setOne(el: ScanUrlEntity)

    @Query("DELETE FROM scanurlentity")
    fun deleteAll()
}