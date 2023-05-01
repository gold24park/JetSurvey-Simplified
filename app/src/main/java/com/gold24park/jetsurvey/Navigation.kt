package com.gold24park.jetsurvey

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gold24park.jetsurvey.Destinations.SURVEY_RESULTS_ROUTE
import com.gold24park.jetsurvey.Destinations.SURVEY_ROUTE
import com.gold24park.jetsurvey.Destinations.WELCOME_ROUTE
import com.gold24park.jetsurvey.login.WelcomeRoute
import com.gold24park.jetsurvey.survey.SurveyResultScreen
import com.gold24park.jetsurvey.survey.SurveyRoute

object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val SURVEY_ROUTE = "survey"
    const val SURVEY_RESULTS_ROUTE = "surveyresults"
}
@Composable
fun JetsurveyNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(onContinue = {
                navController.navigate(SURVEY_ROUTE)
            })
        }

        composable(SURVEY_ROUTE) {
            SurveyRoute(onSurveyComplete = {
                navController.navigate(SURVEY_RESULTS_ROUTE)
            }, onNavUp = navController::navigateUp)
        }

        composable(SURVEY_RESULTS_ROUTE) {
            SurveyResultScreen {
                navController.popBackStack(WELCOME_ROUTE, false)
            }
        }
    }
}