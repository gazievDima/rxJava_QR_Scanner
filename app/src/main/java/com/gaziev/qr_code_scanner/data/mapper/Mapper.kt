package com.gaziev.qr_code_scanner.data.mapper

interface Mapper<T, V> {
    fun mapFrom(t: T): V
    fun mapTo(v: V): T
}