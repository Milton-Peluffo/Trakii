package com.tomildev.room_login_compose.features.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomildev.room_login_compose.core.domain.model.error.DataError
import com.tomildev.room_login_compose.core.domain.model.user.User
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationResult
import com.tomildev.room_login_compose.core.domain.use_case.user.UserUseCases
import com.tomildev.room_login_compose.core.domain.util.Result
import com.tomildev.room_login_compose.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the user registration process.
 *
 * It centralizes the UI state through [RegisterUiState] and orchestrates
 * input validation via [UserUseCases] before proceeding with account
 * creation in [AuthRepository].
 */

sealed interface RegisterUiEvent {
    data class Error(val error: DataError) : RegisterUiEvent
}

@HiltViewModel
class RegisterViewmodel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _uiEvents = Channel<RegisterUiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onRegisterClick() {
        if (validateFields()) {
            registerUser()
        }
    }

    private fun validateFields(): Boolean {
        val state = _uiState.value

        val nameResult = userUseCases.validateName.execute(name = state.name)
        if (nameResult is UserValidationResult.Error) {
            updateErrorState(nameError = nameResult.error)
            return false
        }

        val emailResult = userUseCases.validateEmail.execute(email = state.email)
        if (emailResult is UserValidationResult.Error) {
            updateErrorState(emailError = emailResult.error)
            return false
        }

        val passwordResult = userUseCases.validatePassword.execute(password = state.password)
        if (passwordResult is UserValidationResult.Error) {
            updateErrorState(passwordError = passwordResult.error)
            return false
        }

        val passwordConfirmResult = userUseCases.validateConfirmPassword.execute(
            password = state.password,
            confirmPassword = state.confirmPassword
        )
        if (passwordConfirmResult is UserValidationResult.Error) {
            updateErrorState(passwordConfirmError = passwordConfirmResult.error)
            return false
        }
        updateErrorState()
        return true
    }

    private fun updateErrorState(
        nameError: UserValidationError? = null,
        emailError: UserValidationError? = null,
        passwordError: UserValidationError? = null,
        passwordConfirmError: UserValidationError? = null
    ) {
        _uiState.update {
            it.copy(
                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,
                passwordConfirmError = passwordConfirmError,

                isNameError = nameError != null,
                isEmailError = emailError != null,
                isPasswordError = passwordError != null,
                isPasswordConfirmError = passwordConfirmError != null
            )
        }
    }

    fun registerUser() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = authRepository.signUp(
                user = User(
                    id = "",
                    name = _uiState.value.name,
                    email = _uiState.value.email
                ),
                password = _uiState.value.password
            )
            _uiState.update { it.copy(isLoading = false) }
            when (result) {
                is Result.Error -> {
                    _uiEvents.send(RegisterUiEvent.Error(result.error))
                }

                is Result.Success -> {
                    _uiState.update {
                        it.copy(showSuccessDialog = true)
                    }
                }
            }
        }
    }

    fun onDismissDialog() {
        _uiState.update {
            it.copy(
                showSuccessDialog = false,
            )
        }
    }

    fun onNameChange(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name,
                errorMessage = null,
                isNameError = false
            )
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                errorMessage = null,
                isEmailError = false
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                errorMessage = null,
                isPasswordError = false
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                confirmPassword = confirmPassword,
                errorMessage = null,
                isPasswordConfirmError = false
            )
        }
    }
}

data class RegisterUiState(
    //USER DATA
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    //VALIDATORS
    val showSuccessDialog: Boolean = false,
    val isRegistered: Boolean = false,
    val isLoading: Boolean = false,
    //ERRORS
    val networkError: DataError? = null,
    val errorMessage: String? = null,
    val isNameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val nameError: UserValidationError? = null,
    val emailError: UserValidationError? = null,
    val passwordError: UserValidationError? = null,
    val passwordConfirmError: UserValidationError? = null,
    val isPasswordConfirmError: Boolean = false,
)