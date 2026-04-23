package com.easypark.app.notes.worker

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.easypark.app.MainActivity
import com.easypark.app.notes.data.local.NoteDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.tasks.await

class CacheCleanupWorker(
    context: Context,
    params: WorkerParameters,
    private val database: NoteDatabase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("CacheCleanup", "--- INICIANDO TRABAJO DE LIMPIEZA ---")
        try {
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.fetchAndActivate().await()
            
            val maxLimit = remoteConfig.getLong("max_notes_limit").toInt()
            val finalLimit = if (maxLimit > 0) maxLimit else 10
            
            val noteDao = database.noteDao()
            val countBefore = noteDao.getCount()

            if (countBefore > finalLimit) {
                val toDelete = countBefore - finalLimit
                noteDao.deleteOldest(toDelete)
                
                val countAfter = noteDao.getCount()
                val actuallyDeleted = countBefore - countAfter

                if (actuallyDeleted > 0) {
                    showNotification(actuallyDeleted)
                }
            }

            return Result.success()
        } catch (e: Exception) {
            Log.e("CacheCleanup", "ERROR: ${e.message}")
            return Result.retry()
        }
    }

    private fun showNotification(deletedCount: Int) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Crear el Intent para abrir la App al tocar la notificación
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE // Requerido para versiones modernas de Android
        )

        val builder = NotificationCompat.Builder(applicationContext, "default_channel_id")
            .setSmallIcon(android.R.drawable.ic_menu_delete)
            .setContentTitle("Limpieza de Caché")
            .setContentText("Se han eliminado $deletedCount notas antiguas con éxito.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent) // AGREGADO: Abre la app
            .setAutoCancel(true) // Desaparece al tocarla

        notificationManager.notify(1001, builder.build())
    }
}
