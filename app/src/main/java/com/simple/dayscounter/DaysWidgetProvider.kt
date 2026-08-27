package com.simple.dayscounter

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class DaysWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        updateWidgets(context, appWidgetManager, appWidgetIds)
    }

    companion object {
        fun updateWidgets(context: Context, manager: AppWidgetManager, ids: IntArray) {
            val prefs = context.getSharedPreferences(Prefs.NAME, Context.MODE_PRIVATE)
            val startTime = prefs.getLong(Prefs.START_TIME, -1L)
            val days = if (startTime < 0L) 0 else Prefs.daysSince(startTime)

            for (id in ids) {
                val views = RemoteViews(context.packageName, R.layout.widget_days)
                views.setTextViewText(R.id.widgetCountText, days.toString())

                val launchIntent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(
                    context, 0, launchIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widgetRoot, pendingIntent)

                manager.updateAppWidget(id, views)
            }
        }
    }
}
