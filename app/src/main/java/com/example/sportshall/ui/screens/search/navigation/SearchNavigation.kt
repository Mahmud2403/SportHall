package com.example.sportshall.ui.screens.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.home.navigation.HomeDestination
import com.example.sportshall.ui.screens.search.SearchScreen
import com.example.sportshall.ui.screens.search.SearchViewModel
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation.DetailInformationNavigation

object SearchNavigation : SportHallNavigationDestination {
	override val destination = "search_destination"
	override val route = "search_route"
}

fun NavGraphBuilder.search(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit,
){
	composable(SearchNavigation.route){
		val viewModel = hiltViewModel<SearchViewModel>()

		SearchScreen(
			onClickBack = onClickBack,
			viewModel = viewModel,
			onClickInformation = { id, type ->
				navigateTo (
					HomeDestination,
					DetailInformationNavigation.navigateWithArgument(
						id = id,
						type = type,
					)
				)
			},
		)
	}
}