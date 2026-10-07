package co.uniquindio.seguimientodos.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uniquindio.seguimientodos.ui.components.ColabPrimaryButton
import co.uniquindio.seguimientodos.ui.components.ColabTextField
import co.uniquindio.seguimientodos.ui.theme.*

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColabBackground)
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        IconButton(onClick = onNavigateBack, modifier = Modifier.offset(x = (-12).dp)) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = ColabDarkGreen)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = ColabDarkGreen
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Escribe el correo de tu cuenta y te enviaremos un enlace para crear una contraseña nueva.",
            fontSize = 16.sp,
            color = ColabTextSecondary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        ColabTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = "Correo electrónico",
            placeholder = "tu@correo.com",
            leadingIcon = Icons.Filled.Email,
            errorMessage = emailError
        )

        Spacer(modifier = Modifier.height(24.dp))

        ColabPrimaryButton(
            text = "Enviar enlace de recuperación",
            onClick = {
                if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "Ingresa un correo válido"
                } else {
                    onNavigateBack() // Simulación envío exitoso y regreso al Login
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = ColabSurface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Info, contentDescription = null, tint = ColabTextSecondary)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Sirve para usuarios y moderadores. Si no lo ves en tu bandeja, revisa la carpeta de spam.",
                    color = ColabTextSecondary,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver a iniciar sesión", color = ColabDarkGreen, fontWeight = FontWeight.SemiBold)
        }
    }
}