package com.simple.dayscounter

import java.util.concurrent.TimeUnit

object Prefs {
    const val NAME = "days_counter_prefs"
    const val START_TIME = "start_time"

    fun daysSince(startTime: Long): Int {
        val elapsed = System.currentTimeMillis() - startTime
        if (elapsed < 0) return 0
        return TimeUnit.MILLISECONDS.toDays(elapsed).toInt()
    }
}
