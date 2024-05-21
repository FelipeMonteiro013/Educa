package com.example.educa

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            "match_id",
            "Match Notification",

            NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.description = "Novo Match"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}