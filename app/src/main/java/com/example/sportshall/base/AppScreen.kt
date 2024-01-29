package com.example.sportshall.base

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.sportshall.base.navigation.SportHallNavHost
import com.example.sportshall.base.navigation.TopLevelDestination
import com.example.sportshall.ui.screens.profile.navigation.ProfileDestination
import com.example.sportshall.ui.screens.search.navigation.SearchNavigation
import com.example.sportshall.ui.screens.sport_hall_data.clients.navigation.AddClientsDestination
import com.example.sportshall.ui.screens.sport_hall_data.clients.navigation.ViewClientsDestination
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation.DetailInformationNavigation
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.navigation.AddSeasonTicketNavigation
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.navigation.ViewSeasonTicketNavigation
import com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation.AddTrainerDestination
import com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation.ViewTrainerDestination
import com.example.sportshall.ui.theme.Primary


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppScreen(
	appState: SportHallAppState = rememberSportHallAppState()
) {
	Scaffold(
		modifier = Modifier
			.navigationBarsPadding(),
		bottomBar = {
			AnimatedVisibility(
				visible = shouldShowBottomBar(appState),
				enter = slideInVertically { it },
				exit = slideOutVertically { it },
			) {
			SportHallBottomBar(
				destinations = topLevelDestinations,
				onNavigateToDestination = { appState.navigate(destination = it, null) },
				currentDestination = appState.currentDestination,
			)
			}
		},
//		floatingActionButton = {
//            AnimatedVisibility(
//                visible = shouldShowBottomBar(appState),
//                enter = slideInVertically { it },
//                exit = slideOutVertically { it },
//            ) {
//                SportHallFloatingActionButton(
//                    modifier = Modifier
//                        .padding(top = 30.dp)
//                        .size(62.dp),
//                    onClick = appState::navigate,
//                    destinations = topLevelDestinations[4]
//                )
//            }
//		},
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,
	) {
		SportHallNavHost(
			navController = appState.navController,
			onClickBack = appState::onBackClick,
			onNavigateToDestination = appState::navigate,
			systemUiController = appState.systemUiController,
			modifier = Modifier,
		)
	}
}

@Composable
private fun shouldShowBottomBar(appState: SportHallAppState) =
	with(appState.currentDestination) {
		if (this == null) false
		else if (
			listOf(
				AddTrainerDestination.route,
				AddClientsDestination.route,
				ViewTrainerDestination.route,
				ViewClientsDestination.route,
				DetailInformationNavigation.route,
				AddSeasonTicketNavigation.route,
				ViewSeasonTicketNavigation.route,
				SearchNavigation.route,
			).contains(route)
		)
			false
		else !listOf(
			ProfileDestination.destination
		).contains(parent?.route)
	}

@Composable
fun SportHallFloatingActionButton(
	modifier: Modifier = Modifier,
	onClick: (TopLevelDestination) -> Unit,
	destinations: TopLevelDestination,
) {
	FloatingActionButton(
		modifier = modifier,
		backgroundColor = Color.White,
		elevation = FloatingActionButtonDefaults.elevation(),
		onClick = { onClick(destinations) }) {
		Icon(
			tint = Color.White,
			modifier = Modifier.size(22.dp),
			painter = painterResource(id = destinations.icon),
			contentDescription = null
		)
	}
}


@Composable
fun SportHallBottomBar(
	modifier: Modifier = Modifier,
	destinations: List<TopLevelDestination>,
	onNavigateToDestination: (TopLevelDestination) -> Unit,
	currentDestination: NavDestination?,
) {

	Surface(
		modifier = modifier
			.fillMaxWidth()
			.shadow(
				elevation = 20.dp,
				shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
			)
			.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))

	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(bottom = 5.dp, top = 3.dp, start = 3.dp, end = 3.dp)
				.selectableGroup(),
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			destinations.forEach { destination ->
				val isSelected = remember(currentDestination) {
					currentDestination?.hierarchy?.any {
						it.route == destination.route
					} == true
				}
				SportHallBottomBarItem(
					selected = isSelected,
					onClick = onNavigateToDestination,
					destination = destination,
				)
			}
		}
	}
}

@Composable
fun SportHallBottomBarItem(
	onClick: (TopLevelDestination) -> Unit,
	selected: Boolean = false,
	destination: TopLevelDestination,
) {
	Column(
		modifier = Modifier
			.padding(vertical = 4.dp)
			.clickable(
				interactionSource = remember { MutableInteractionSource() },
				indication = rememberRipple(bounded = false),
				onClick = { onClick(destination) }),
		verticalArrangement = Arrangement.spacedBy(7.dp)
	) {
		Icon(
			modifier = Modifier
				.align(Alignment.CenterHorizontally)
				.size(22.dp),
			painter = painterResource(id = destination.icon),
			contentDescription = null,
            tint = if (selected) Primary else Color.Black
		)
		Text(
			modifier = Modifier.padding(horizontal = 15.dp),
			text = destination.title,
			style = MaterialTheme.typography.labelSmall,
            color = if (selected) Primary else Color.Black
		)
	}
}
