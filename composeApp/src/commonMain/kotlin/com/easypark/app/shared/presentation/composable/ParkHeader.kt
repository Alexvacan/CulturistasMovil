package com.easypark.app.shared.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easypark.app.shared.ui.ParkBlueLight
import com.easypark.app.shared.ui.ParkTextDark
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_back
import kotlinproject.composeapp.generated.resources.ic_notification
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun ParkHeader(
    title: String,
    onBackClick: (() -> Unit)? = null, // Si es null, no sale la flecha
    onNotificationClick: (() -> Unit)? = null // Si es null, no sale la campana
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón Atrás (Solo si se pasa la función)
        Box(modifier = Modifier.size(40.dp)) {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Image(
                        painter = painterResource(Res.drawable.ic_back),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Título Central
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = ParkTextDark
        )

        // Botón Notificación (Solo si se pasa la función)
        Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
            if (onNotificationClick != null) {
                Surface(
                    modifier = Modifier.size(40.dp).clickable { onNotificationClick() },
                    shape = CircleShape,
                    color = ParkBlueLight
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_notification),
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
