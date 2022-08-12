package com.dev0.deliveryfood.core.utils

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dev0.deliveryfood.feacture_news.domain.model.New
import com.dev0.deliveryfood.feacture_news.domain.model.NewInFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import javax.inject.Inject

class UpdateNewsWorker (appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    val repository = DB.db?.newsDao

    override fun doWork(): Result {


        val database = Firebase.database
        val myRef = database.getReference("news")
        myRef.get().addOnCompleteListener {
            val dataSnapshot: DataSnapshot = it.result

            val listItems: ArrayList<NewInFirebase> = ArrayList()

            for(ds in dataSnapshot.children){
                val n: NewInFirebase = ds.getValue(NewInFirebase::class.java)!!
                listItems.add(n)
            }

            Log.e("SE han traido ", ""+listItems.size)


            //drop table
            repository?.deleteAll()

            listItems.forEach { newInfirebase ->

                Log.e("url", ""+newInfirebase.imageUrl)


                repository?.insert(
                    New(
                        name = newInfirebase.name!!,
                        description = newInfirebase.description!!,
                        imageUrl = newInfirebase.imageUrl!!,
                        id = null
                    )
                )

            }
        }

        return Result.success()
    }
}