package com.gold24park.jetsurvey.survey.question

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R
import com.gold24park.jetsurvey.ui.theme.JetsurveyTheme

@Composable
fun SliderQuestion(
    @StringRes titleResourceId: Int,
    value: Float?,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 3,
    @StringRes startTextResource: Int,
    @StringRes neutralTextResource: Int,
    @StringRes endTextResource: Int,
    modifier: Modifier = Modifier,
) {
    QuestionWrapper(
        titleResourceId = titleResourceId,
        modifier = modifier,
    ) {
        var sliderPosition by remember {
            mutableStateOf(value ?: ((valueRange.endInclusive - valueRange.start) / 2))
        }

        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                onValueChange(it)
            },
            valueRange = valueRange,
            steps = steps,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = startTextResource),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1.8f)
            )
            Text(
                text = stringResource(id = neutralTextResource),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1.8f)
            )
            Text(
                text = stringResource(id = endTextResource),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1.8f)
            )
        }
    }
}

@Preview
@Composable
fun SliderQuestionPreview() {
    JetsurveyTheme() {
        SliderQuestion(
            titleResourceId = R.string.selfies,
            value = 0f,
            onValueChange = {},
            startTextResource = R.string.strongly_dislike,
            neutralTextResource = R.string.neutral,
            endTextResource = R.string.strongly_like,
        )
    }
}