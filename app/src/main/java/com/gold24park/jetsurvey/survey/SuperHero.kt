package com.gold24park.jetsurvey.survey

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Superhero(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)