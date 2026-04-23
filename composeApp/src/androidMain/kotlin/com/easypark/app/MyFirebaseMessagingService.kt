package com.easypark.app

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "Mensaje recibido de: ${remoteMessage.from}")
        
        // Aquí puedes manejar la notificación si la app está en primer plano
        remoteMessage.notification?.let {
            Log.d("FCM", "Cuerpo del mensaje: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Nuevo token: $token")
        // Aquí deberías enviar el token a tu servidor si es necesario
    }
}