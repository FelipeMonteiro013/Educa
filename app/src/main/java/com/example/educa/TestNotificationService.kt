package com.example.educa

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class TestNotificationService(
    private val context: Context
) {

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification() {
        val notification =
            NotificationCompat.Builder(context, "test_id").setContentTitle("Test Notification")
                .setContentText("Testando notificação")
                .setSmallIcon(R.drawable.baseline_image_not_supported_24)
                .setPriority(NotificationManager.IMPORTANCE_HIGH).setAutoCancel(true).build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources, resId
    )
}