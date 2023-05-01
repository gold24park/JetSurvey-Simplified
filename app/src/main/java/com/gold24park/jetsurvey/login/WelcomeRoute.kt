package com.gold24park.jetsurvey.login

import androidx.compose.runtime.Composable

@Composable
fun WelcomeRoute(
    onContinue: () -> Unit,
) {
    WelcomeScreen(
        onContinue = onContinue
    )
}