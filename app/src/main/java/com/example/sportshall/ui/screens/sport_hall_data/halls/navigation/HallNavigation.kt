package com.example.sportshall.ui.screens.sport_hall_data.halls.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.sport_hall_data.halls.HallScreen

object HallDestination : SportHallNavigationDestination {
	override val route = "hall_route"
	override val destination = "hall_destination"
}

fun NavGraphBuilder.hall(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit
){
	composable(route = HallDestination.route){
		HallScreen(
			onClickBack = onClickBack
		)
	}
}