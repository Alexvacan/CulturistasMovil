package com.easypark.app.notifications.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easypark.app.notifications.domain.model.NotificationModel
import com.easypark.app.notifications.presentation.viewmodel.NotificationsViewModel
import com.easypark.app.shared.presentation.composable.ParkHeader
import com.easypark.app.shared.ui.ParkBlueLight
import com.easypark.app.shared.ui.ParkGray
import com.easypark.app.shared.ui.ParkTextDark
import org.jetbrains.compose.resources.painterResource

@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            ParkHeader(
                title = "Notificaciones",
                onBackClick = onBack,
                onNotificationClick = null
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            if (state.list.isEmpty() && !state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No hay más notificaciones recientes",
                        color = ParkGray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.list.size) { index ->
                        val notification = state.list[index]
                        NotificationItem(
                            notification = notification,
                            onClick = { /* Acción al hacer click */ }
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 0.5.dp,
                            color = ParkGray.copy(alpha = 0.2f)
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "No hay más notificaciones recientes",
                            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                            textAlign = TextAlign.Center,
                            color = ParkGray,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationItem(
    notification: NotificationModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Container
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(ParkBlueLight),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(notification.icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            if (notification.isUnread) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(ParkTextDark)
                        .padding(2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text Content
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = notification.title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = ParkTextDark,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification.description,
                fontSize = 13.sp,
                color = ParkGray,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Time and Arrow
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = notification.time,
                fontSize = 12.sp,
                color = ParkGray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(">", color = ParkGray, fontSize = 12.sp)
        }
    }
}
