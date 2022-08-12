package com.dev0.deliveryfood.core.utils

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dev0.deliveryfood.feacture_news.domain.model.NewInFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SendCommentWorker (appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        val comment = inputData.getString("comment") ?: return Result.failure()

        val userId = "9876543210000000"

        val database = Firebase.database
        val myRef = database.getReference("comments").child(userId).push()
        myRef.setValue(comment)

        return Result.success()
    }
}