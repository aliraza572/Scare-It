package com.aliraza.scareit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.prankSurface).setOnClickListener {
            createNotification()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun createNotification() {
        val requestId = System.currentTimeMillis().toInt()

        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationIntent = Intent(this, SurpriseActivity::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val contentIntent = PendingIntent.getActivity(
            this,
            requestId,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val message = "Tap to shock friends"
        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Scary Notification")
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(alarmSound)
            .setContentIntent(contentIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "MyChannelId"
            val channel = NotificationChannel(
                channelId,
                "channel title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelId)
        }

        notificationManager.notify(4000, builder.build())

    }
}