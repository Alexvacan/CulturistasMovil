package com.easypark.app.shared.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easypark.app.shared.ui.ParkBlue
import com.easypark.app.shared.ui.ParkGray
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_calendar
import kotlinproject.composeapp.generated.resources.ic_garage
import kotlinproject.composeapp.generated.resources.ic_home
import org.jetbrains.compose.resources.painterResource

@Composable
private fun FooterItem(
    label: String,
    imageRes: org.jetbrains.compose.resources.DrawableResource,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) ParkBlue else ParkGray

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(color) // Esto pintará tu JPG del color del estado
        )
        Text(
            text = label,
            color = color,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

// --- FOOTER CONDUCTOR ---
@Composable
fun DriverFooter(
    currentScreen: String,
    onNavigate: (String) -> Unit
) {
    Surface(shadowElevation = 8.dp, color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            FooterItem(
                label = "Inicio",
                imageRes = Res.drawable.ic_home,
                isSelected = currentScreen == "home_driver",
                onClick = { onNavigate("home_driver") }
            )
            FooterItem(
                label = "Mis Reservas",
                imageRes = Res.drawable.ic_calendar,
                isSelected = currentScreen == "reservas_driver",
                onClick = { onNavigate("reservas_driver") }
            )
        }
    }
}

// --- FOOTER DUEÑO ---
@Composable
fun OwnerFooter(
    currentScreen: String,
    onNavigate: (String) -> Unit
) {
    Surface(shadowElevation = 8.dp, color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            FooterItem(
                label = "Reservas",
                imageRes = Res.drawable.ic_calendar,
                isSelected = currentScreen == "reservas_owner",
                onClick = { onNavigate("reservas_owner") }
            )
            FooterItem(
                label = "Inicio",
                imageRes = Res.drawable.ic_home,
                isSelected = currentScreen == "home_owner",
                onClick = { onNavigate("home_owner") }
            )
            FooterItem(
                label = "Espacios",
                imageRes = Res.drawable.ic_garage,
                isSelected = currentScreen == "espacios_owner",
                onClick = { onNavigate("espacios_owner") }
            )
        }
    }
}