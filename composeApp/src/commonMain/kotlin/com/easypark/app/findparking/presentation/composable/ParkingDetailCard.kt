package com.easypark.app.findparking.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easypark.app.shared.domain.model.ParkingModel
import com.easypark.app.shared.presentation.composable.ParkButton
import com.easypark.app.shared.presentation.composable.ParkCard
import com.easypark.app.shared.ui.ParkBlue
import com.easypark.app.shared.ui.ParkError
import com.easypark.app.shared.ui.ParkSuccess

@Composable
fun ParkingDetailCard(
    parking: ParkingModel,
    onReserve: () -> Unit,
    onDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    ParkCard(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = parking.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${parking.pricePerHour} Bs/hora",
                    color = ParkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = if (parking.isAvailable) "Disponible" else "Lleno",
                    color = if (parking.isAvailable) ParkSuccess else ParkError,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ParkButton(
                        text = "Reservar",
                        onClick = onReserve,
                        enabled = parking.isAvailable
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    ParkButton(
                        text = "Ver Detalles",
                        onClick = onDetails,
                        isSecondary = true
                    )
                }
            }
        }
    }
}