package com.tomildev.room_login_compose.core.common.presentation.components.snackbars

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.tomildev.room_login_compose.R
import com.tomildev.room_login_compose.ui.theme.ErrorRed
import com.tomildev.room_login_compose.ui.theme.InfoBlue
import com.tomildev.room_login_compose.ui.theme.SuccessGreen
import com.tomildev.room_login_compose.ui.theme.WarningOrange

object SnackBars {

    @Composable
    fun Success(
        title: String,
        description: String? = null,
        onClick: () -> Unit
    ) {
        SnackbarBase(
            title = title,
            description = description,
            icon = painterResource(R.drawable.ic_success),
            iconTint = SuccessGreen,
            onClick = onClick
        )
    }

    @Composable
    fun Suggestion(
        title: String,
        description: String? = null,
        onClick: () -> Unit
    ) {
        SnackbarBase(
            title = title,
            description = description,
            icon = painterResource(R.drawable.ic_info),
            iconTint = InfoBlue,
            onClick = onClick
        )
    }

    @Composable
    fun Warning(
        title: String,
        description: String? = null,
        onClick: () -> Unit
    ) {
        SnackbarBase(
            title = title,
            description = description,
            icon = painterResource(R.drawable.ic_warning),
            iconTint = WarningOrange,
            onClick = onClick
        )
    }

    @Composable
    fun Error(
        title: String,
        description: String? = null,
        onClick: () -> Unit
    ) {
        SnackbarBase(
            title = title,
            description = description,
            icon = painterResource(R.drawable.ic_error),
            iconTint = ErrorRed,
            onClick = onClick
        )
    }
}