package com.gold24park.jetsurvey.survey

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gold24park.jetsurvey.util.PhotoUriManager
import com.google.android.material.datepicker.MaterialDatePicker

private const val CONTENT_ANIMATION_DURATION = 300

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SurveyRoute(
    onSurveyComplete: () -> Unit,
    onNavUp: () -> Unit
) {

    val viewModel: SurveyViewModel = viewModel(
        factory = SurveyViewModelFactory(PhotoUriManager(LocalContext.current))
    )

    BackHandler {
        if (!viewModel.onBackPressed()) {
            onNavUp()
        }
    }

    val surveyScreenData = viewModel.surveyScreenData

    SurveyQuestionScreen(
        surveyScreenData = surveyScreenData,
        isNextEnabled = viewModel.isNextEnabled,
        onClosePressed = { onNavUp() },
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = { viewModel.onDonePressed(onSurveyComplete) }) { paddingValues ->
        
        val modifier = Modifier.padding(paddingValues)
        
        AnimatedContent(
            targetState = surveyScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> = tween(CONTENT_ANIMATION_DURATION)
                val direction = getTransitionDirection(
                    initialIndex = initialState.questionIndex,
                    targetIndex = targetState.questionIndex
                )

                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec,
                ) with slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            }
        ) { targetState ->
            when (targetState.surveyQuestion) {
                SurveyQuestion.FREE_TIME -> {
                    FreeTimeQuestion(
                        selectedAnswers = viewModel.freeTimeResponse,
                        onOptionSelected = viewModel::onFreeTimeResponse,
                        modifier = modifier
                    )
                }
                SurveyQuestion.SUPERHERO -> {
                    SuperheroQuestion(
                        selectedAnswer = viewModel.superheroResponse,
                        onOptionSelected = viewModel::onSuperheroResponse,
                        modifier = modifier
                    )
                }
                SurveyQuestion.LAST_TAKEAWAY -> {
                    val supportFragmentManager =
                        LocalContext.current.findActivity().supportFragmentManager
                    TakeawayQuestion(
                        modifier = modifier,
                        dateInMillis = viewModel.takeawayResponse,
                        onClick = {
                            showTakeawayDatePicker(
                                date = viewModel.takeawayResponse,
                                supportFragmentManager = supportFragmentManager,
                                onDateSelected = viewModel::onTakeawayResponse
                            )
                        }
                    )
                }
                SurveyQuestion.FEELING_ABOUT_SELFIES -> {
                    FeelingAboutSelfiesQuestion(
                        value = viewModel.feelingAboutSelfiesResponse,
                        onValueChange = viewModel::onFeelingAboutSelfiesResponse,
                        modifier = modifier
                    )
                }
                SurveyQuestion.TAKE_SELFIE -> {
                    TakeSelfieQuestion(
                        imageUri = viewModel.selfieUri,
                        getNewImageUri = viewModel::getNewSelfieUri,
                        onPhotoTaken = viewModel::onSelfieResponse,
                        modifier = modifier
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int,
): AnimatedContentScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        AnimatedContentScope.SlideDirection.Left
    } else {
        AnimatedContentScope.SlideDirection.Right
    }
}

private fun showTakeawayDatePicker(
    date: Long?,
    supportFragmentManager: FragmentManager,
    onDateSelected: (date: Long) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker()
        .setSelection(date)
        .build()

    picker.show(supportFragmentManager, picker.toString())

    picker.addOnPositiveButtonClickListener {
        picker.selection?.let(onDateSelected)
    }
}

private tailrec fun Context.findActivity(): AppCompatActivity =
    when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> this.baseContext.findActivity()
        else -> throw IllegalArgumentException("Could not find activity! $this")
    }
