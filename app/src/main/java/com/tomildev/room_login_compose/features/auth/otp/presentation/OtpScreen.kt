package com.tomildev.room_login_compose.features.auth.otp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomildev.room_login_compose.core.common.presentation.components.buttons.PrimaryButton
import com.tomildev.room_login_compose.core.common.presentation.components.spacers.VerticalSpacer
import com.tomildev.room_login_compose.core.common.presentation.components.texts.Texts
import com.tomildev.room_login_compose.features.auth.otp.presentation.components.CustomNumericKeyboard
import com.tomildev.room_login_compose.features.auth.otp.presentation.components.InputDigitBox
import com.tomildev.room_login_compose.ui.theme.Dimens

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    otpViewModel: OtpViewModel = viewModel()
) {

    val state by otpViewModel.uiState.collectAsStateWithLifecycle()
    val digits by otpViewModel.digitList.collectAsStateWithLifecycle()


    Scaffold { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                digits.forEach { digit ->
                    InputDigitBox(number = digit)
                }
            }
            VerticalSpacer(Dimens.SpacingLarge)
            TextButton(onClick = {}) {
                Texts.TitleMedium(
                    text = "Resend code",
                )
            }
            VerticalSpacer(Dimens.SpacingLarge)
            PrimaryButton(text = "Verify", onClick = { })
            VerticalSpacer(Dimens.SpacingLarge)
            CustomNumericKeyboard(
                onNumberClick = { otpViewModel.onNumberclick(it) },
                onDeleteClick = { otpViewModel.onDeleteClick() }
            )
        }
    }
}