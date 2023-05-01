package com.gold24park.jetsurvey

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.gold24park.jetsurvey.ui.theme.JetsurveyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetsurveyTheme {
                JetsurveyNavHost()
            }
        }
    }
}
