package com.example.homeworks

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseServices:FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("QQQQ", message.toString())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}