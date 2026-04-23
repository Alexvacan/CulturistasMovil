package com.easypark.app.signin.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.easypark.app.navigation.NavRoute
import com.easypark.app.shared.domain.model.UserType
import com.easypark.app.shared.presentation.composable.ParkButton
import com.easypark.app.shared.presentation.composable.ParkTextField
import com.easypark.app.signin.presentation.state.*
import com.easypark.app.signin.presentation.viewmodel.SignInViewModel
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SignInEffect.NavigateToHome -> {
                    val route = if (effect.userType == UserType.OWNER) NavRoute.SpaceManagement else NavRoute.FindParking
                    navController.navigate(route) {
                        popUpTo(NavRoute.SignIn) { inclusive = true }
                    }
                }
                is SignInEffect.NavigateToRegister -> navController.navigate(NavRoute.Register)
                is SignInEffect.ShowError -> println(effect.message)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Nueva Imagen logov2
        Image(
            painter = painterResource(Res.drawable.logov2),
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )

        // Texto solicitado: Culturistas
        Text(
            text = "Culturistas",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                letterSpacing = 2.sp
            ),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        ParkTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(SignInEvent.OnEmailChange(it)) },
            placeholder = stringResource(Res.string.hint_email),
            isError = state.isEmailError,
            label = stringResource(Res.string.label_email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ParkTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(SignInEvent.OnPasswordChange(it)) },
            placeholder = "********",
            isError = state.isPasswordError,
            isPassword = true,
            label = stringResource(Res.string.label_password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÓN RENOMBRADO: Ingresar sin Inicio de sesión
        ParkButton(
            onClick = { navController.navigate(NavRoute.FindParking) },
            text = "Ingresar sin Inicio de sesión"
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(Res.string.signin_or_separator))
        Spacer(modifier = Modifier.height(16.dp))

        ParkButton(
            onClick = { viewModel.onEvent(SignInEvent.OnRegisterClick) },
            text = stringResource(Res.string.action_create_account),
            isSecondary = true
        )
    }
}