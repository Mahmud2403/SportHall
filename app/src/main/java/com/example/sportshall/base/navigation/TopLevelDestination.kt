package com.example.sportshall.base.navigation

import androidx.annotation.DrawableRes
import com.example.sportshall.base.navigation.SportHallNavigationDestination


data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val icon: Int,
    val title: String,
): SportHallNavigationDestination