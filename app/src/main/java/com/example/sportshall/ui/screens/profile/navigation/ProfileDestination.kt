package com.example.sportshall.ui.screens.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.profile.ProfileScreen

object ProfileDestination : SportHallNavigationDestination {
	override val route = "profile_route"
	override val destination = "profile_destination"
}

fun NavGraphBuilder.profileGraph(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	nestedGraphs: NavGraphBuilder.() -> Unit,
){
	navigation(
		route = ProfileDestination.route,
		startDestination = ProfileDestination.destination,
	){
		nestedGraphs()
	}
}

fun NavGraphBuilder.profile(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit
){
	composable(route = ProfileDestination.destination){
		ProfileScreen()
	}
}