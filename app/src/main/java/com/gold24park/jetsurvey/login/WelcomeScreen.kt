package com.gold24park.jetsurvey.login

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R
import com.gold24park.jetsurvey.ui.theme.JetsurveyTheme
import com.gold24park.jetsurvey.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun WelcomeScreen(
    onContinue: () -> Unit
) {
    var showBranding by remember { mutableStateOf(true) }

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier
                .weight(1F, fill = showBranding)
                .animateContentSize()
            )

            AnimatedVisibility(
                visible = showBranding,
                modifier = Modifier.fillMaxWidth()
            ) {
                Branding()
            }

            Spacer(modifier = Modifier
                .weight(1F, fill = showBranding)
                .animateContentSize()
            )

            SignInCreateAccount(
                onContinue = onContinue,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
private fun Branding(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Logo(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 24.dp)
        )
        Text(
            text = stringResource(id = R.string.app_tagline),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun Logo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = LocalContentColor.current.luminance() < 0.5F
) {
    val assetId = if (lightTheme) {
        R.drawable.ic_logo_light
    } else {
        R.drawable.ic_logo_dark
    }

    Image(
        painter = painterResource(id = assetId),
        modifier = modifier,
        contentDescription = null
    )
}

@Composable
private fun SignInCreateAccount(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.sign_in_guest),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )

        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 3.dp)
        ) {
            Text(
                text = stringResource(id = R.string.user_continue),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Preview(name = "light theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenPreview() {
    JetsurveyTheme {
        WelcomeScreen {

        }
    }
}