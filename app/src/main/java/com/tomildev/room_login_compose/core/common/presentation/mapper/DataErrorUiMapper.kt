package com.tomildev.room_login_compose.core.common.presentation.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tomildev.room_login_compose.R
import com.tomildev.room_login_compose.core.common.presentation.util.UiText
import com.tomildev.room_login_compose.core.domain.model.error.DataError
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError

@Composable
fun UserValidationError.asString(): String {
    return when (this) {
        is UserValidationError.EmptyField -> stringResource(R.string.error_empty_field)
        is UserValidationError.TooShortName -> stringResource(R.string.error_user_name_too_short)
        is UserValidationError.InvalidName -> stringResource(R.string.error_user_name_invalid)
        is UserValidationError.InvalidEmail -> stringResource(R.string.error_email_invalid)
        is UserValidationError.TooShortPassword -> stringResource(R.string.error_password_too_short)
        is UserValidationError.InvalidPassword -> stringResource(R.string.error_password_format)
        is UserValidationError.PasswordDoNotMatch -> stringResource(R.string.error_confirm_password_match)
    }
}

fun DataError.toUiText(): UiText {
    return when (this) {
        is DataError.Network -> {
            val redId = when (this) {
                DataError.Network.ServiceUnavailable -> R.string.register_network_service_unavailable_error
                DataError.Network.Conflict -> R.string.register_network_email_already_exists_error
                DataError.Network.NoInternet -> R.string.register_network_no_internet_error
                DataError.Network.Timeout -> R.string.register_network_timeout_error
                DataError.Network.Unknown -> R.string.register_network_unknown_error
            }
            UiText.StringResource(redId)
        }
    }
}