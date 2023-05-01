package com.gold24park.jetsurvey.survey.question

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R
import com.gold24park.jetsurvey.util.getDefaultDateInMillis
import com.gold24park.jetsurvey.survey.simpleDateFormatPattern
import com.gold24park.jetsurvey.ui.theme.JetsurveyTheme
import com.gold24park.jetsurvey.ui.theme.slightlyDeemphasizedAlpha
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@Composable
fun DateQuestion(
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int,
    dateInMillis: Long?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuestionWrapper(
        titleResourceId = titleResourceId,
        directionsResourceId = directionsResourceId,
        modifier = modifier
    ) {
        val dateFormat = SimpleDateFormat(simpleDateFormatPattern, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateString = dateFormat.format(dateInMillis ?: getDefaultDateInMillis())

        Button(
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
                    .copy(alpha = slightlyDeemphasizedAlpha)
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)),
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(text = dateString, modifier = Modifier
                .fillMaxWidth()
                .weight(1.8f))
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2F)
            )
        }
    }
}

// preview for DateQuestion Component
@Preview
@Composable
fun DateQuestionPreview() {
    JetsurveyTheme() {
        DateQuestion(
            titleResourceId = R.string.takeaway,
            directionsResourceId = R.string.select_date,
            dateInMillis = Calendar.getInstance().timeInMillis,
            onClick = {

            }
        )
    }
}