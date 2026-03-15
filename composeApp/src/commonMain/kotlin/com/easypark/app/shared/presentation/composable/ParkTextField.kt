package com.easypark.app.shared.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easypark.app.shared.ui.ParkBlueLight
import com.easypark.app.shared.ui.ParkGray
import com.easypark.app.shared.ui.ParkTextDark
import org.jetbrains.compose.resources.painterResource

@Composable
fun ParkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingImage: org.jetbrains.compose.resources.DrawableResource? = null,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ParkTextDark)
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = ParkGray) },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ParkBlueLight,
                unfocusedContainerColor = ParkBlueLight,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = leadingImage?.let {
                { Image(painterResource(it), null, Modifier.size(20.dp)) }
            }
        )
    }
}