package com.gold24park.jetsurvey.survey

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R
import com.gold24park.jetsurvey.survey.question.QuestionWrapper

@Composable
fun MultipleChoiceQuestion(
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int,
    possibleAnswers: List<Int>,
    selectedAnswers: List<Int>,
    onOptionSelected: (selected: Boolean, answer: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    QuestionWrapper(
        modifier = modifier,
        titleResourceId = titleResourceId,
        directionsResourceId = directionsResourceId,
    ) {
        possibleAnswers.forEach {
            val selected = selectedAnswers.contains(it)
            CheckBoxRow(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(id = it),
                selected = selected,
                onOptionSelected = {
                    onOptionSelected(!selected, it)
                }
            )
        }
    }
}

@Composable
fun CheckBoxRow(
    text: String,
    selected: Boolean,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onOptionSelected)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                modifier = modifier
                    .weight(1F),
                style = MaterialTheme.typography.bodyLarge
            )
            Checkbox(
                checked = selected,
                onCheckedChange = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun MultipleChoiceQuestionPreview() {
    val possibleAnswers = listOf(R.string.read, R.string.work_out, R.string.draw)
    val selectedAnswers = remember { mutableStateListOf(R.string.work_out) }
    MultipleChoiceQuestion(
        titleResourceId = R.string.in_my_free_time,
        directionsResourceId = R.string.select_all,
        possibleAnswers = possibleAnswers,
        selectedAnswers = selectedAnswers,
        onOptionSelected = { _, _ -> }
    )
}