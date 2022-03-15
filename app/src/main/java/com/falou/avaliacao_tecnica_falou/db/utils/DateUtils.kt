package com.falou.avaliacao_tecnica_falou.db.utils

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    fun convertStringToDate(date: String): Date {
        val formato = SimpleDateFormat("yyyyMMdd HH:mm:ss")
        val dataFormatada = formato.parse(date)

        return dataFormatada
    }

    fun convertDateToString(date: Date): String{
        val formatDate = SimpleDateFormat("yyyyMMdd HH:mm:ss")
        return formatDate.format(date)
    }

    fun getDay(date: Date):Int{
        val format: Format = SimpleDateFormat("dd")
        val day = format.format(date).toInt()
        return day
    }
}