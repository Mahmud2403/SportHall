package com.example.sportshall.ui.screens.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.home.HomeScreen
import com.example.sportshall.ui.screens.search.navigation.SearchNavigation
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientViewModel

object HomeDestination : SportHallNavigationDestination {
	override val route = "main_route"
	override val destination = "main_destination"
}

fun NavGraphBuilder.homeGraph(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	nestedGraphs: NavGraphBuilder.() -> Unit,
){
	navigation(
		route = HomeDestination.route,
		startDestination = HomeDestination.destination,
	){
		nestedGraphs()
	}
}

fun NavGraphBuilder.home(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit,
){
	composable(route = HomeDestination.destination){
		HomeScreen(
			onClickSearchBar = {
				navigateTo(SearchNavigation, null)
			},
			onClickBack = onClickBack,
		)
	}
}