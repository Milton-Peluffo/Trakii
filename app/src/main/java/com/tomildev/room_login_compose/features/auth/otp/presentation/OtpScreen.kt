package com.tomildev.room_login_compose.features.auth.otp.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomildev.room_login_compose.core.common.presentation.components.buttons.PrimaryButton
import com.tomildev.room_login_compose.core.common.presentation.components.spacers.HorizontalSpacer
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
    val activeIndex by otpViewModel.activeIndex.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val isLandScape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val verticalGap = if (isLandScape) Dimens.SpacingSmall else Dimens.SpacingLarge
    val headerGap = if (isLandScape) Dimens.SpacingTiny else Dimens.SpacingSmall

    val otpFormContent = @Composable {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (isLandScape) 4.dp else 0.dp)
        ) {
            Texts.Headline(text = "Check your inbox")
            VerticalSpacer(headerGap)
            Texts.TitleMedium(
                text = "We have sent you a code",
                isSecondary = true,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(verticalGap)
            Texts.TitleMedium(text = "xxxx@gmail.com", isSecondary = false)
            VerticalSpacer(verticalGap)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                digits.forEachIndexed { index, digit ->
                    InputDigitBox(
                        number = digit,
                        isCursorVisible = index == activeIndex
                    )
                }
            }

            VerticalSpacer(verticalGap)

            TextButton(onClick = {}) {
                Texts.TitleMedium(text = "Resend code")
            }

            VerticalSpacer(verticalGap)
            PrimaryButton(
                text = "Verify",
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    val keyboardContent = @Composable {
        Box(contentAlignment = Alignment.Center) {
            CustomNumericKeyboard(
                onNumberClick = { otpViewModel.onNumberClick(it) },
                onDeleteClick = { otpViewModel.onDeleteClick() },
                onClearAll = { otpViewModel.clearAll() }
            )
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = if (isLandScape) 20.dp else 40.dp),
        ) {
            if (isLandScape) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1.1f)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        otpFormContent()
                    }
                    HorizontalSpacer(Dimens.SpacingLarge)
                    Box(
                        modifier = Modifier.weight(0.9f),
                        contentAlignment = Alignment.Center
                    ) {
                        keyboardContent()
                    }
                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    VerticalSpacer(Dimens.ScreenPadding)
                    otpFormContent()
                    VerticalSpacer(Dimens.SpacingLarge)
                    keyboardContent()
                }
            }
        }
    }
}