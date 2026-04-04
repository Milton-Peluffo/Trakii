package com.tomildev.room_login_compose.features.auth.otp.presentation

import com.tomildev.room_login_compose.core.domain.model.error.DataError

data class OtpUiState(
    val code: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val isVerifyEnable: Boolean = false,
    val isVerified: Boolean = false,
    val error: DataError.Network? = null
)