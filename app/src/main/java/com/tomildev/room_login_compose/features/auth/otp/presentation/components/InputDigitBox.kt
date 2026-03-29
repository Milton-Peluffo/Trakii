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
import com.tomildev.room_login_compose.ui.theme.ExtendedTheme

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
    isCursorVisible: Boolean,
    isOtpCorrect: Boolean = false,
    isOtpInCorrect: Boolean = false
) {
    val shape = MaterialTheme.shapes.large
    val isNumber = number.isNotBlank()
    val borderColor = when {
        isOtpCorrect && isNumber -> ExtendedTheme.colors.success
        isOtpInCorrect && isNumber -> MaterialTheme.colorScheme.error
        !isCursorVisible && isNumber -> Color.LightGray
        isCursorVisible -> Color.LightGray
        else -> MaterialTheme.colorScheme.outline
    }
    val borderWidth =
        when {
            isOtpCorrect && isNumber || isOtpInCorrect && isNumber -> 1.dp
            isCursorVisible -> 2.dp
            else -> 1.dp
        }
    Box(
        modifier = Modifier
            .size(Dimens.OtpBoxSize)
            .clip(shape = shape)
            .border(
                width = borderWidth,
                shape = shape,
                color = borderColor
            )
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {

        val numberColor = when {
            isOtpCorrect -> ExtendedTheme.colors.success
            isOtpInCorrect -> MaterialTheme.colorScheme.error
            else -> Color.Unspecified
        }

        when {
            isNumber -> Texts.TitleLarge(text = number, color = numberColor)
            isCursorVisible -> BlinkingCursor()
            else -> Texts.TitleLarge(text = "—", isSecondary = true, color = Color.Unspecified)
        }
    }
}