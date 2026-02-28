package com.tomildev.room_login_compose.core.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tomildev.room_login_compose.R
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError

@Composable
fun UserValidationError.asString(): String {
    return when (this) {
        is UserValidationError.EmptyField -> stringResource(R.string.error_empty_field)
        is UserValidationError.TooShort -> stringResource(R.string.error_password_too_short)
        is UserValidationError.InvalidFormat -> {
            stringResource(R.string.error_email_invalid)
        }
    }
}