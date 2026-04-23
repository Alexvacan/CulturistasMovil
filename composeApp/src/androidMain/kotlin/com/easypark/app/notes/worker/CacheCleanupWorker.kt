package com.easypark.app.notes.worker

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.easypark.app.notes.data.local.NoteDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import android.app.NotificationManager

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
            Log.d("CacheCleanup", "Notas antes de limpiar: $countBefore. Límite: $finalLimit")

            if (countBefore > finalLimit) {
                val toDelete = countBefore - finalLimit
                noteDao.deleteOldest(toDelete)
                
                // Verificación real post-limpieza
                val countAfter = noteDao.getCount()
                val actuallyDeleted = countBefore - countAfter

                if (actuallyDeleted > 0) {
                    showNotification(actuallyDeleted)
                    Log.d("CacheCleanup", "Limpieza real: $actuallyDeleted notas eliminadas.")
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
        val builder = NotificationCompat.Builder(applicationContext, "default_channel_id")
            .setSmallIcon(android.R.drawable.ic_menu_delete)
            .setContentTitle("Limpieza de Caché")
            .setContentText("Se han eliminado $deletedCount notas antiguas con éxito.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        notificationManager.notify(1001, builder.build())
    }
}
