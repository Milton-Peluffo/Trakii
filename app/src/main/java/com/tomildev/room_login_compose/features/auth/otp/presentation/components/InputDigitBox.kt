package com.tomildev.room_login_compose.features.auth.otp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tomildev.room_login_compose.core.common.presentation.components.texts.Texts
import com.tomildev.room_login_compose.ui.theme.Dimens

/**
 * A composable that represents a single digit input box within an OTP (One-Time Password) field.
 *
 * Displays the provided digit, a blinking cursor if the box is currently active and empty,
 * or a placeholder character if the box is empty and not focused.
 *
 * @param number The digit string to be displayed in the box.
 * @param isCursorVisible Whether the blinking cursor should be shown, indicating the box is currently focused.
 */
@Composable
fun InputDigitBox(
    number: String,
    isCursorVisible: Boolean
) {
    val shape = MaterialTheme.shapes.large
    Box(
        modifier = Modifier
            .size(Dimens.OtpBoxSize)
            .clip(shape = shape)
            .border(
                width = if (isCursorVisible) 2.dp else 1.dp,
                shape = shape,
                color = if (isCursorVisible) Color.LightGray else MaterialTheme.colorScheme.outline
            )
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        when {
            number.isNotBlank() -> Texts.TitleLarge(text = number)
            isCursorVisible -> BlinkingCursor()
            else -> Texts.TitleLarge(text = "—", isSecondary = true)
        }
    }
}