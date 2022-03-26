package com.example.homeworks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.databinding.ActivityMainBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID = "CHANNEL_ID"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        binding.button.setOnClickListener {
            checkGooglePlayServices()
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("QQQQ", "failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result
                Log.d("QQQQ", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })
        }

    }


    private fun createNotificationChannel() {
        val name = "channel_name"
        val descriptionText = "channel_desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }


    private fun checkGooglePlayServices(): Boolean {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        return if (status != ConnectionResult.SUCCESS) {
            Log.e("QQQQ", "Error")
            false
        } else {
            Log.i("QQQQ", "OK")
            true
        }
    }

}