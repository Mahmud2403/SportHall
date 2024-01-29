package com.example.sportshall.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sportshall.R
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.base.navigation.TopLevelDestination
import com.example.sportshall.ui.screens.home.navigation.HomeDestination
import com.example.sportshall.ui.screens.information.navigation.InformationDestination
import com.example.sportshall.ui.screens.profile.navigation.ProfileDestination
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val topLevelDestinations: List<TopLevelDestination> = listOf(
	TopLevelDestination(
		route = HomeDestination.route,
		destination = HomeDestination.destination,
		icon = R.drawable.ic_house,
		title = "Home",
	),
	TopLevelDestination(
		route = InformationDestination.route,
		destination = InformationDestination.destination,
		icon = R.drawable.ic_add,
		title = "Information",
	),
	TopLevelDestination(
		route = ProfileDestination.route,
		destination = ProfileDestination.destination,
		icon = R.drawable.ic_profile,
		title = "Profile"
	)
)

@Composable
fun rememberSportHallAppState(
	navController: NavHostController = rememberNavController(),
	systemUiController: SystemUiController = rememberSystemUiController(),
): SportHallAppState {
	return remember(navController) { SportHallAppState(navController, systemUiController) }
}

@Stable
class SportHallAppState constructor(
	val navController: NavHostController,
	val systemUiController: SystemUiController,
) {

	val currentDestination: NavDestination?
		@Composable get() = navController.currentBackStackEntryAsState().value?.destination

	fun navigate(destination: SportHallNavigationDestination, route: String? = null) {
		if (destination is TopLevelDestination) {
			navController.navigate(route ?: destination.route) {
				popUpTo(navController.graph.findStartDestination().id) { saveState = true }
				launchSingleTop = true
				restoreState = true
			}
		} else {
			navController.navigate(route ?: destination.route) {
				launchSingleTop = true
			}
		}
	}

	fun onBackClick() {
		navController.popBackStack()
	}
}