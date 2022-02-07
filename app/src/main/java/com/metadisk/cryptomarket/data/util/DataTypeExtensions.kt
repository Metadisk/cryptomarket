package com.metadisk.cryptomarket.data.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun String?.trimParanthesis(): String {
    return this?.replace(Regex("[()]"), "") ?: ""
}

@SuppressLint("SimpleDateFormat")
fun String?.formattedDate(dateFormat: DateFormat = DateFormat.getDateInstance()): Date? =
    this?.let {
        try {
            SimpleDateFormat(dateFormat.toString()).parse(this)
        } catch (e: Exception) {
            null
        }
    }

fun Double?.dollarString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        "US$ ${numberFormat.format(this)}"
    } ?: ""
}