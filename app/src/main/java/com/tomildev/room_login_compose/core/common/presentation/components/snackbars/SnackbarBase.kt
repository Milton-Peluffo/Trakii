package com.tomildev.room_login_compose.core.common.presentation.components.snackbars

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomildev.room_login_compose.R

@Composable
fun SnackbarBase(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    icon: Painter,
    iconTint: Color,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(20.dp)

    Snackbar(
        modifier = modifier
            .height(65.dp)
            .border(width = 2.dp, shape = shape, color = iconTint.copy(alpha = 0.5f)),
        shape = shape,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        action = {
            IconButton(modifier = Modifier.fillMaxHeight(), onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = icon,
                contentDescription = null,
                tint = iconTint
            )
            Column(
                modifier = Modifier.padding(horizontal = 15.dp),
            ) {
                Text(
                    title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (!description.isNullOrBlank()) {
                    Text(
                        text = description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}