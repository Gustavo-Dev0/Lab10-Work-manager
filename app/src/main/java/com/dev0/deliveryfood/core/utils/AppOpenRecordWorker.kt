package com.dev0.deliveryfood.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class AppOpenRecordWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {

        Thread.sleep(5000)

        val userId = "9876543210000000"
        var date = LocalDateTime.now()

        val database = Firebase.database
        val myRef = database.getReference("open_app_register").child(userId).push()
        myRef.setValue(date)

        return Result.success()
    }
}
