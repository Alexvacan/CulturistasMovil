package com.easypark.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.easypark.app.navigation.AppNavHost

@Composable
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}
