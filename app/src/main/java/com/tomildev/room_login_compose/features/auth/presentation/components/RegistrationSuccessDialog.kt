package com.tomildev.room_login_compose.features.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun RegistrationSuccessDialog(
    modifier: Modifier = Modifier,
    title: String = "account created successfully!",
    description: String = "Please check your email to verify your account",
    confirmText: String = "OK",
    onConfirm: () -> Unit = {},
) {


    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onConfirm() },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = description,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
            ) {
                Text(
                    text = confirmText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    )
}