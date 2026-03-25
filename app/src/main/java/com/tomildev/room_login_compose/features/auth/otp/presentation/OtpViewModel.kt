package com.tomildev.room_login_compose.features.auth.otp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OtpViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState: StateFlow<OtpUiState> = _uiState.asStateFlow()

    /**
     * A [StateFlow] representing the OTP code as a list of four individual strings.
     * Each element corresponds to a position in the 4-digit code (indices 0 to 3).
     * If a digit has not been entered yet, the value at that index defaults to an empty string.
     */
    val digitList: StateFlow<List<String>> = _uiState
        .map { state ->
            (0..3).map { index ->
                state.code.getOrNull(index)?.toString() ?: ""
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = listOf("", "", "", "")
        )

    /**
     * A [StateFlow] representing the index of the next digit to be entered in the OTP sequence.
     * The value ranges from 0 to 3, corresponding to the current input position.
     * If all 4 digits have been entered, the value becomes -1.
     */
    val activeIndex: StateFlow<Int> = _uiState
        .map { state ->
            if (state.code.length < 4) state.code.length else -1
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )


    fun onNumberClick(number: String) {
        val currentCode = _uiState.value.code
        if (currentCode.length < 4) {
            val newCode = currentCode + number
            _uiState.update {
                it.copy(code = newCode, isVerifyEnable = newCode.length == 4)
            }
        }
    }

    fun onDeleteClick() {
        val currentCode = _uiState.value.code
        if (currentCode.isNotEmpty()) {
            val newCode = currentCode.dropLast(1)
            _uiState.update {
                it.copy(code = newCode, isVerifyEnable = false)
            }
        }
    }

    fun clearAll() {
        _uiState.update {
            it.copy(code = "", isVerifyEnable = false)
        }
    }
}

data class OtpUiState(
    val code: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val isVerifyEnable: Boolean = false
)