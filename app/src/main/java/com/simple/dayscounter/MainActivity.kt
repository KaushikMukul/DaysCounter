package com.simple.dayscounter

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: android.content.SharedPreferences
    private lateinit var countText: TextView
    private lateinit var subText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(Prefs.NAME, Context.MODE_PRIVATE)
        countText = findViewById(R.id.countText)
        subText = findViewById(R.id.subText)

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.text = "Reset (start over)"
        resetButton.setOnClickListener {
            prefs.edit().putLong(Prefs.START_TIME, System.currentTimeMillis()).apply()
            refreshUi()
            updateAllWidgets()
        }

        refreshUi()
    }

    override fun onResume() {
        super.onResume()
        refreshUi()
    }

    private fun refreshUi() {
        val startTime = prefs.getLong(Prefs.START_TIME, -1L)
        if (startTime < 0L) {
            countText.text = "0"
            subText.text = "Tap reset to start your streak"
        } else {
            val days = Prefs.daysSince(startTime)
            countText.text = days.toString()
            subText.text = if (days == 1) "1 day" else "$days days"
        }
    }

    private fun updateAllWidgets() {
        val manager = AppWidgetManager.getInstance(this)
        val ids = manager.getAppWidgetIds(ComponentName(this, DaysWidgetProvider::class.java))
        DaysWidgetProvider.updateWidgets(this, manager, ids)
    }
}
