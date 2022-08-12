package com.dev0.deliveryfood.core.utils

import android.content.Context
import androidx.core.app.NotificationCompat

import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import androidx.work.Worker
import androidx.work.WorkerParameters

class MakeNotificationWorker (appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        generateNotification()

        return Result.success()
    }

    private fun generateNotification() {
        val channelId = "1"


        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Android")
            .setContentText("Gracias")
            .setSubText("Tu comentario nos ayuda a mejorar")
            .setAutoCancel(true)
            .build()

    }



}