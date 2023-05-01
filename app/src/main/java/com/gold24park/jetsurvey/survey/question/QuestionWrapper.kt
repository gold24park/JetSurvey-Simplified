package com.gold24park.jetsurvey.survey.question

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gold24park.jetsurvey.ui.theme.slightlyDeemphasizedAlpha
import com.gold24park.jetsurvey.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun QuestionWrapper(
    @StringRes titleResourceId: Int,
    modifier: Modifier = Modifier,
    @StringRes directionsResourceId: Int? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        QuestionTitle(title = titleResourceId)
        directionsResourceId?.let {
            Spacer(modifier = Modifier.height(18.dp))
            QuestionDirections(it)
        }
        Spacer(modifier = Modifier.height(18.dp))

        content()
    }
}
@Composable
fun QuestionTitle(
    @StringRes title: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = slightlyDeemphasizedAlpha),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = MaterialTheme.shapes.small,
            )
            .padding(horizontal = 16.dp, vertical = 24.dp)
    )
}

@Composable
fun QuestionDirections(
    @StringRes directionsResourceId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = directionsResourceId),
        color = MaterialTheme.colorScheme.onSurface
            .copy(alpha = stronglyDeemphasizedAlpha),
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}