package com.example.sportshall.ui.screens.sport_hall_data.type_of_sport.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.sport_hall_data.halls.HallScreen
import com.example.sportshall.ui.screens.sport_hall_data.type_of_sport.TypeOfSportScreen

object TypeOfSportDestination : SportHallNavigationDestination {
	override val route = "type_of_sport_route"
	override val destination = "type_of_sport_destination"
}

fun NavGraphBuilder.sport(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit
){
	composable(route = TypeOfSportDestination.route){
		TypeOfSportScreen(
			onClickBack = onClickBack
		)
	}
}