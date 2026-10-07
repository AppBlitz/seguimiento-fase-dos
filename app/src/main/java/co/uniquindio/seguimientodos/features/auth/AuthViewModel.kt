package co.uniquindio.seguimientodos.features.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun updateIsLogin(isLogin: Boolean) {
        _uiState.update { it.copy(isLoginMode = isLogin) }
    }

    fun onFullNameChange(name: String) {
        _uiState.update { it.copy(fullName = name, nameError = null) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onConfirmPasswordChange(password: String) {
        _uiState.update { it.copy(confirmPassword = password, confirmPasswordError = null) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun toggleTermsAccepted(accepted: Boolean) {
        _uiState.update { it.copy(termsAccepted = accepted, termsError = null) }
    }

    fun validateLogin(): Boolean {
        val state = _uiState.value
        var isValid = true

        if (state.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            _uiState.update { it.copy(emailError = "Correo electrónico inválido") }
            isValid = false
        }
        if (state.password.isBlank()) {
            _uiState.update { it.copy(passwordError = "La contraseña es obligatoria") }
            isValid = false
        }

        return isValid
    }

    fun validateRegistration(): Boolean {
        val state = _uiState.value
        var isValid = true

        if (state.fullName.isBlank()) {
            _uiState.update { it.copy(nameError = "El nombre es obligatorio") }
            isValid = false
        }
        if (state.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            _uiState.update { it.copy(emailError = "Correo electrónico inválido") }
            isValid = false
        }
        if (state.password.length < 8) {
            _uiState.update { it.copy(passwordError = "Mínimo 8 caracteres") }
            isValid = false
        }
        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(confirmPasswordError = "Las contraseñas no coinciden") }
            isValid = false
        }
        if (!state.termsAccepted) {
            _uiState.update { it.copy(termsError = "Debes aceptar los términos y condiciones") }
            isValid = false
        }

        return isValid
    }
}

data class AuthUiState(
    val isLoginMode: Boolean = true,
    val fullName: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val passwordVisible: Boolean = false,
    val address: String = "Lat 4.5350, Lng -75.6750",
    val termsAccepted: Boolean = false,
    val termsError: String? = null
)