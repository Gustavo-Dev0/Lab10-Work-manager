package com.dev0.deliveryfood

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.net.URL


class MyFirebaseMessagingService: FirebaseMessagingService () {

    companion object {
        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
    }

    override fun onNewToken(token: String) {
        Log.e(TAG, "token: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        var title = message.notification?.title
        var body = message.notification?.body
        var image = message.notification?.imageUrl?.toString()
        var map: String? = null
        var tel: String? = null

        val data: Map<String, String> = message.data
        if(data.keys.isNotEmpty()) {

            title = data["title"]
            body = data["info"]
            image = data["imageUrl"]
            map = data["ubiMap"]
            tel = data["telNum"]

            Log.e("MapSize", "  " + data.keys.size)
        }




        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder = if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationCompat.Builder(applicationContext, "1")
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        //SIN LAYOUT PERSONALIZADO
        /*notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.ic_baseline_restaurant_24)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())*/

        //CON LAYOUT PERSONALIZADO
        val notificationLayout = RemoteViews(packageName, R.layout.notification_collapsed)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.notification_expanded)

        notificationLayout.setTextViewText(R.id.collapsed_notification_title, title)
        notificationLayout.setTextViewText(R.id.collapsed_notification_info, body)
        notificationLayoutExpanded.setTextViewText(R.id.expanded_notification_title, title)
        notificationLayoutExpanded.setTextViewText(R.id.expanded_notification_info, body)

        if(tel != null){
            val intentCall = Intent()
            intentCall.action = Intent.ACTION_DIAL
            intentCall.data = Uri.parse(tel)

            val pendingIntentC: PendingIntent? = PendingIntent.getActivity(this, 0, intentCall, 0)
            notificationLayout.setOnClickPendingIntent(R.id.callNumber, pendingIntentC)
            notificationLayoutExpanded.setOnClickPendingIntent(R.id.callNumberExpanded, pendingIntentC)
        }

        if(map != null){
            val mapsUri = Uri.parse(map)
            val intentMap = Intent(Intent.ACTION_VIEW, mapsUri)
            intentMap.setPackage("com.google.android.apps.maps")
            val pendingIntentM: PendingIntent? = PendingIntent.getActivity(this, 0, intentMap, 0)
            notificationLayout.setOnClickPendingIntent(R.id.openMap, pendingIntentM)
            notificationLayoutExpanded.setOnClickPendingIntent(R.id.openMapExpanded, pendingIntentM)
        }

        if(image != null){
            val url = URL(image.toString());
            val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            notificationLayoutExpanded.setImageViewBitmap(R.id.image_view_expanded, bmp)

            val intentWeb = Intent()
            intentWeb.action = Intent.ACTION_VIEW
            intentWeb.data = Uri.parse(image.toString())

            val pendingIntent: PendingIntent? = PendingIntent.getActivity(this, 0, intentWeb, 0)
            notificationLayoutExpanded.setOnClickPendingIntent(R.id.image_view_expanded, pendingIntent)
        }


        notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.ic_baseline_restaurant_24)
            .setColor(Color.RED)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)

        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }


    private fun initNotificationChannel(notificationManager: NotificationManager){
        if(Build.VERSION_CODES.O <= Build.VERSION.SDK_INT){
            var channel = notificationManager.getNotificationChannel("1")
            if(channel == null){
                channel = NotificationChannel("1", "Default", NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}