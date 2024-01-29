package com.example.sportshall.base.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sportshall.ui.screens.home.navigation.HomeDestination
import com.example.sportshall.ui.screens.home.navigation.home
import com.example.sportshall.ui.screens.home.navigation.homeGraph
import com.example.sportshall.ui.screens.information.navigation.information
import com.example.sportshall.ui.screens.information.navigation.informationGraph
import com.example.sportshall.ui.screens.profile.navigation.profile
import com.example.sportshall.ui.screens.profile.navigation.profileGraph
import com.example.sportshall.ui.screens.search.navigation.search
import com.example.sportshall.ui.screens.sport_hall_data.clients.navigation.client
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation.detailInfo
import com.example.sportshall.ui.screens.sport_hall_data.halls.navigation.hall
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.navigation.seasonTicket
import com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation.trainer
import com.example.sportshall.ui.screens.sport_hall_data.type_of_sport.navigation.sport
import com.google.accompanist.systemuicontroller.SystemUiController


@Composable
fun SportHallNavHost(
	modifier: Modifier = Modifier,
	navController: NavHostController,
	onNavigateToDestination: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	systemUiController: SystemUiController,
	onClickBack: () -> Unit,
) {

	NavHost(
		modifier = modifier,
		navController = navController,
		startDestination = HomeDestination.route,
	) {
		homeGraph(
			navigateTo = onNavigateToDestination,

		){
			home(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack
			)
			search(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack
			)
		}

		informationGraph(
			navigateTo = onNavigateToDestination
		){
			client(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack
			)
			trainer(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack
			)
			information(
				onNavigateToDestination
			)
			sport(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack,
			)
			hall(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack,
			)
			seasonTicket(
				navigateTo = onNavigateToDestination,
				onClickBack = onClickBack,
			)
			detailInfo(
				onClickBack = onClickBack,
			)
		}

		profileGraph(
			navigateTo = onNavigateToDestination
		){
			profile(
				navigateTo = onNavigateToDestination
			)
		}
	}
}


const val DURATION_NAVIGATION_ANIMATION = 250

fun AnimatedContentTransitionScope<NavBackStackEntry>.currentRout() = initialState.destination.route

fun AnimatedContentTransitionScope<NavBackStackEntry>.targetRout() = targetState.destination.route

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainer(direction: Direction): EnterTransition {
	val slideDirection = when (direction) {
		Direction.Right -> AnimatedContentTransitionScope.SlideDirection.Right
		Direction.Left -> AnimatedContentTransitionScope.SlideDirection.Left
		Direction.Up -> AnimatedContentTransitionScope.SlideDirection.Up
		Direction.Down -> AnimatedContentTransitionScope.SlideDirection.Down
	}
	return slideIntoContainer(
		slideDirection,
		animationSpec = tween(
			durationMillis = DURATION_NAVIGATION_ANIMATION,
			easing = EaseInOutQuad
		)
	)
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainer(direction: Direction): ExitTransition {
	val slideDirection = when (direction) {
		Direction.Right -> AnimatedContentTransitionScope.SlideDirection.Right
		Direction.Left -> AnimatedContentTransitionScope.SlideDirection.Left
		Direction.Up -> AnimatedContentTransitionScope.SlideDirection.Up
		Direction.Down -> AnimatedContentTransitionScope.SlideDirection.Down
	}
	return slideOutOfContainer(
		slideDirection,
		animationSpec = tween(
			durationMillis = DURATION_NAVIGATION_ANIMATION,
			easing = EaseInOutQuad
		)
	)
}

enum class Direction {
	Right, Left, Up, Down
}

