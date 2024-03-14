package com.example.networknewsfragment.objects

import java.util.Calendar

object DateUtil {
    fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        return "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.DAY_OF_MONTH)}"
    }
}