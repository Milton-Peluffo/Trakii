package com.tomildev.room_login_compose.features.auth.otp.presentation

data class OtpUiState(
    val code: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val isVerifyEnable: Boolean = false
)