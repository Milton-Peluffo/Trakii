package com.tomildev.room_login_compose.features.auth.otp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import com.tomildev.room_login_compose.core.common.presentation.components.texts.Texts
import com.tomildev.room_login_compose.ui.theme.Dimens

@Composable
fun NumericKey(
    modifier: Modifier = Modifier,
    text: String = "",
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(Dimens.KeySize)
            .clip(CircleShape)
            .let {
                if (text != "" || icon != null) it.clickable(
                    onClick = onClick,
                    interactionSource = androidx.compose.runtime.remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    indication = androidx.compose.material3.ripple(
                        color = androidx.compose.ui.graphics.Color.LightGray,
                        bounded = true
                    ),
                ) else it
            },
        contentAlignment = Alignment.Center
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(Dimens.IconSizeMedium)
            )
        } else {
            Texts.Headline(text = text)
        }
    }

}