package co.uniquindio.seguimientodos.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.uniquindio.seguimientodos.features.auth.AuthScreen
import co.uniquindio.seguimientodos.features.auth.AuthViewModel
import co.uniquindio.seguimientodos.features.auth.ForgotPasswordScreen
import co.uniquindio.seguimientodos.features.welcome.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(
                onNavigateToAuth = {
                    navController.navigate("auth") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
        composable("auth") {
            val authViewModel: AuthViewModel = viewModel()
            AuthScreen(
                viewModel = authViewModel,
                onNavigateToHome = {
                    /* Destino temporal a implementar en la siguiente fase (Interfaz Usuario) */
                },
                onNavigateToForgotPassword = { navController.navigate("forgot_password") }
            )
        }
        composable("forgot_password") {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}