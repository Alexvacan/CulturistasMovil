package com.easypark.app

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.work.*
import com.easypark.app.notes.worker.CacheCleanupWorker
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.messaging.FirebaseMessaging
import org.osmdroid.config.Configuration
import java.util.Date
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("FCM_TEST", "Permiso de notificaciones concedido")
        } else {
            Log.e("FCM_TEST", "Permiso de notificaciones denegado")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        createNotificationChannel()
        askNotificationPermission()
        setupCacheCleanupWorker()

        Log.e("FCM_TEST", "--- INICIO DE APP ---")
        if (checkGooglePlayServices()) {
            getFCMToken()
        }

        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))
        setContent { App() }
    }

    private fun setupCacheCleanupWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val cleanupRequest = PeriodicWorkRequestBuilder<CacheCleanupWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("CacheCleanup")
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "CacheCleanupWork",
            ExistingPeriodicWorkPolicy.KEEP,
            cleanupRequest
        )
        
        // Disparar una limpieza inmediata para prueba (opcional)
        val oneTimeRequest = OneTimeWorkRequestBuilder<CacheCleanupWorker>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(oneTimeRequest)
        
        Log.d("CacheCleanup", "Worker de limpieza programado")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones_Default"
            val descriptionText = "Canal para notificaciones de prueba y sistema"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("default_channel_id", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun checkGooglePlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            apiAvailability.makeGooglePlayServicesAvailable(this)
            return false
        }
        return true
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("FCM_TEST", "¡TOKEN OBTENIDO!: ${task.result}")
            }
        }
    }
}