package co.uniquindio.seguimientodos.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uniquindio.seguimientodos.ui.components.ColabPrimaryButton
import co.uniquindio.seguimientodos.ui.components.ColabTabToggle
import co.uniquindio.seguimientodos.ui.components.ColabTextField
import co.uniquindio.seguimientodos.ui.theme.*

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColabDarkGreen)
    ) {
        // Cabecera superior institucional del mockup
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "coLab",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "- tour -",
                color = ColabBrown,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (uiState.isLoginMode) "Explora. Comparte. Descubre." else "Únete a la comunidad de exploradores",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // Contenedor principal estilo tarjeta inferior
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(ColabSurface)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ColabTabToggle(
                        isLoginSelected = uiState.isLoginMode,
                        onTabSelected = { viewModel.updateIsLogin(it) },
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }

                if (uiState.isLoginMode) {
                    item { LoginSection(uiState, viewModel, onNavigateToHome, onNavigateToForgotPassword) }
                } else {
                    item { RegisterSection(uiState, viewModel, onNavigateToHome) }
                }
            }
        }
    }
}

@Composable
fun LoginSection(
    uiState: AuthUiState,
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        ColabTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = "Correo electrónico",
            placeholder = "tu@correo.com",
            leadingIcon = Icons.Filled.Email,
            errorMessage = uiState.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        ColabTextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange,
            label = "Contraseña",
            placeholder = "Tu contraseña",
            leadingIcon = Icons.Filled.Lock,
            isPassword = true,
            passwordVisible = uiState.passwordVisible,
            onPasswordVisibilityChange = viewModel::togglePasswordVisibility,
            errorMessage = uiState.passwordError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¿Olvidaste tu contraseña? Recuperar",
            color = ColabDarkGreen,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onNavigateToForgotPassword() }
        )

        Spacer(modifier = Modifier.height(32.dp))

        ColabPrimaryButton(
            text = "Iniciar sesión",
            onClick = {
                if (viewModel.validateLogin()) {
                    onLoginSuccess() // Delegamos acción exitosa
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Moderadores: ingresen con las credenciales asignadas",
            color = ColabTextSecondary,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RegisterSection(
    uiState: AuthUiState,
    viewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        ColabTextField(
            value = uiState.fullName,
            onValueChange = viewModel::onFullNameChange,
            label = "Nombre completo",
            placeholder = "Tu nombre",
            leadingIcon = Icons.Filled.Person,
            errorMessage = uiState.nameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        ColabTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = "Correo electrónico",
            placeholder = "tu@correo.com",
            leadingIcon = Icons.Filled.Email,
            errorMessage = uiState.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        ColabTextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange,
            label = "Contraseña",
            placeholder = "Mínimo 8 caracteres",
            leadingIcon = Icons.Filled.Lock,
            isPassword = true,
            passwordVisible = uiState.passwordVisible,
            onPasswordVisibilityChange = viewModel::togglePasswordVisibility,
            errorMessage = uiState.passwordError
        )
        Spacer(modifier = Modifier.height(16.dp))
        ColabTextField(
            value = uiState.confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChange,
            label = "Confirmar contraseña",
            placeholder = "Repite tu contraseña",
            leadingIcon = Icons.Filled.Lock,
            isPassword = true,
            passwordVisible = uiState.passwordVisible,
            onPasswordVisibilityChange = viewModel::togglePasswordVisibility,
            errorMessage = uiState.confirmPasswordError
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Simulación visual requerida para el componente de mapa
        Text("Tu dirección", color = ColabTextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, ColabGrayBorder, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Place, contentDescription = null, tint = ColabTextSecondary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Elegir ubicación en el mapa", color = ColabTextSecondary)
            }
        }
        Text("Guardamos latitud y longitud al elegir el punto en el mapa.", fontSize = 12.sp, color = ColabTextSecondary, modifier = Modifier.padding(top = 4.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = uiState.termsAccepted,
                onCheckedChange = { viewModel.toggleTermsAccepted(it) },
                colors = CheckboxDefaults.colors(checkedColor = ColabDarkGreen)
            )
            Text("Acepto los términos y la política de privacidad", fontSize = 14.sp, color = ColabTextPrimary)
        }
        if (uiState.termsError != null) {
            Text(uiState.termsError!!, color = ColabError, fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        ColabPrimaryButton(
            text = "Crear cuenta",
            onClick = {
                if (viewModel.validateRegistration()) {
                    onRegisterSuccess() // Delegamos registro exitoso
                }
            }
        )
    }
}