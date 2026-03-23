package com.tomildev.room_login_compose.features.auth.otp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomildev.room_login_compose.core.common.presentation.components.spacers.VerticalSpacer
import com.tomildev.room_login_compose.core.common.presentation.components.texts.Texts
import com.tomildev.room_login_compose.ui.theme.Dimens

@Composable
fun OtpScreen(modifier: Modifier = Modifier) {

    Scaffold() { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            VerticalSpacer(Dimens.ScreenPadding)
            Texts.Headline(text = "Check your inbox")
            VerticalSpacer(Dimens.SpacingSmall)
            Texts.TitleMedium(
                text = "We have sent you a verification code by email",
                isSecondary = true,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(Dimens.SpacingLarge)
            Texts.TitleMedium(text = "xxxx@gmail.com", isSecondary = false)
            VerticalSpacer(Dimens.SpacingLarge)
            Row(modifier = Modifier) { repeat(4) { InputDigitBox() } }
            VerticalSpacer(Dimens.SpacingLarge)
            Texts.TitleMedium(
                text = "Resend code",
                modifier = Modifier.clickable { /* action */ }
            )
        }
    }

}

@Composable
fun InputDigitBox() {

    Box(
        modifier = Modifier
            .size(Dimens.OtpBoxSize)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "")
    }

}