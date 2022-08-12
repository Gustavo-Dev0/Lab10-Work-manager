package com.dev0.deliveryfood

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*
import androidx.work.*
import com.dev0.deliveryfood.core.data.repository.Theme
import com.dev0.deliveryfood.core.data.repository.UserPreferencesRepository
import com.dev0.deliveryfood.core.presentation.MainScreen
import com.dev0.deliveryfood.core.utils.AppOpenRecordWorker
import com.dev0.deliveryfood.core.utils.UpdateNewsWorker
import com.dev0.deliveryfood.ui.theme.DeliveryFoodTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val appThemeState = mutableStateOf(Theme.DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferencesRepository = UserPreferencesRepository(dataStore)
        recoverFromDataStore()
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful){
                    //Log.e("FCM Notify", "ERROR REGISTRANDO TOKEN", task.exception)
                    return@OnCompleteListener
                }
                val token: String? = task.result
                //Log.e("FCM Notify", token, task.exception)
                //Toast.makeText(this, token, Toast.LENGTH_SHORT).show()

            })
        Firebase.messaging.subscribeToTopic("foods")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                //Log.d("MainActivity", msg)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        createNotificationChannel()
        startWorkers()


        setContent {

            DeliveryFoodTheme(
                darkTheme = false,
                appTheme = appThemeState.value
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(appThemeState){
                        lifecycleScope.launch{
                            userPreferencesRepository.updateAppTheme(appThemeState.value)
                        }
                    }

                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun startWorkers() {

        //Una sola vez

        val oneWorkRequest =
            OneTimeWorkRequestBuilder<AppOpenRecordWorker>()
                .build()

        WorkManager
            .getInstance(applicationContext)
            .enqueueUniqueWork(
                "appOpenRecord",
                ExistingWorkPolicy.REPLACE,
                oneWorkRequest
            )

        //Cada 15 min

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<UpdateNewsWorker>(
            15, TimeUnit.MINUTES)
            .build()

        WorkManager
            .getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "updateNews",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )

    }

    private fun recoverFromDataStore() {
        lifecycleScope.launch{
            userPreferencesRepository.userPreferencesFlow.collect { userPreferences ->
                appThemeState.value = userPreferences.appTheme

            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Principal"
            val channelId = "1"
            val descriptionText = "None"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val nm: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(channel)
        }
    }

}