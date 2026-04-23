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

            // Configurar para que la actualización sea casi instantánea en pruebas
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
            remoteConfig.setConfigSettingsAsync(configSettings)

            Log.d("CacheCleanup", "Intentando obtener límite de Firebase...")
            remoteConfig.fetchAndActivate().await()
            
            val maxLimit = remoteConfig.getLong("max_notes_limit").toInt()
            Log.d("CacheCleanup", "Límite detectado (Firebase): $maxLimit")

            // Si Firebase devuelve 0 (porque no cargó bien), usamos 10 por seguridad para tu prueba
            val finalLimit = if (maxLimit > 0) maxLimit else 10
            
            val noteDao = database.noteDao()
            val currentCount = noteDao.getCount()
            Log.d("CacheCleanup", "Notas actuales en base de datos: $currentCount")

            if (currentCount > finalLimit) {
                val toDelete = currentCount - finalLimit
                Log.d("CacheCleanup", "Exceso detectado. Eliminando $toDelete notas antiguas...")
                noteDao.deleteOldest(toDelete)
                
                showNotification(toDelete)
                Log.d("CacheCleanup", "Limpieza completada exitosamente.")
            } else {
                Log.d("CacheCleanup", "No es necesario limpiar. Todo bajo el límite.")
            }

            return Result.success()
        } catch (e: Exception) {
            Log.e("CacheCleanup", "ERROR en el Worker: ${e.message}")
            return Result.retry()
        }
    }

    private fun showNotification(deletedCount: Int) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(applicationContext, "default_channel_id")
            .setSmallIcon(android.R.drawable.ic_menu_delete) // Cambiado a un icono de borrar
            .setContentTitle("Culturistas: Limpieza de Caché")
            .setContentText("Se han eliminado $deletedCount notas antiguas para optimizar espacio.")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Prioridad alta para que aparezca arriba
            .setAutoCancel(true)

        notificationManager.notify(1001, builder.build())
    }
}
