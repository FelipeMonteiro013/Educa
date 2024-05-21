package com.example.educa

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class MatchNotificationService(
    private val context: Context
) {

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(loggedUserName: String, userName: String) {
        val notification =
            NotificationCompat.Builder(context, "match_id")
                .setContentTitle("Parabéns, $loggedUserName!")
                .setContentText("Você tem um novo match com $userName!")
                .setSmallIcon(R.drawable.baseline_menu_book_24)
                .setPriority(NotificationManager.IMPORTANCE_HIGH).setAutoCancel(true).build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

}