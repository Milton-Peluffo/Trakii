package com.tomildev.room_login_compose.core.common.presentation.components.snackbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.tomildev.room_login_compose.R
import com.tomildev.room_login_compose.ui.theme.ErrorRed
import com.tomildev.room_login_compose.ui.theme.InfoBlue
import com.tomildev.room_login_compose.ui.theme.SuccessGreen
import com.tomildev.room_login_compose.ui.theme.WarningOrange

private enum class SnackbarStyle(
    val iconRes: Int,
    val color: @Composable () -> Color
) {
    Success(R.drawable.ic_success, { SuccessGreen }),
    Info(R.drawable.ic_info, { InfoBlue }),
    Warning(R.drawable.ic_warning, { WarningOrange }),
    Error(R.drawable.ic_error, { ErrorRed })
}

object SnackBars {

    @Composable
    fun Success(
        title: String,
        modifier: Modifier = Modifier,
        description: String? = null,
        onClick: () -> Unit
    ) = SnackbarElement(
        modifier,
        SnackbarStyle.Success,
        title = title,
        description = description,
        onClick = onClick
    )

    @Composable
    fun Info(
        title: String,
        modifier: Modifier = Modifier,
        description: String? = null,
        onClick: () -> Unit
    ) = SnackbarElement(
        modifier,
        SnackbarStyle.Info,
        title = title,
        description = description,
        onClick = onClick
    )

    @Composable
    fun Warning(
        title: String,
        modifier: Modifier = Modifier,
        description: String? = null,
        onClick: () -> Unit
    ) = SnackbarElement(
        modifier,
        SnackbarStyle.Warning,
        title = title,
        description = description,
        onClick = onClick
    )

    @Composable
    fun Error(
        title: String,
        modifier: Modifier = Modifier,
        description: String? = null,
        onClick: () -> Unit
    ) = SnackbarElement(
        modifier,
        SnackbarStyle.Error,
        title = title,
        description = description,
        onClick = onClick
    )

    @Composable
    private fun SnackbarElement(
        modifier: Modifier,
        style: SnackbarStyle,
        title: String,
        description: String?,
        onClick: () -> Unit
    ) {
        SnackbarBase(
            modifier = modifier,
            title = title,
            description = description,
            icon = painterResource(style.iconRes),
            iconTint = style.color(),
            onClick = onClick
        )
    }
}