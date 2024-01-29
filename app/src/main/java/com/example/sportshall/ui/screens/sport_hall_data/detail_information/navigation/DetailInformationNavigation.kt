package com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.DetailInformationScreen
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.DetailInformationViewModel
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.TypeDetailInformation

object DetailInformationNavigation : SportHallNavigationDestination {
	override val route = "detail_info_route/{id}/{type}"
	override val destination = "detail_info_destination"
	fun navigateWithArgument(id: Int, type: String) =
		"detail_info_route/$id/$type"
}

fun NavGraphBuilder.detailInfo(
	onClickBack: () -> Unit
) {
	composable(
		route = DetailInformationNavigation.route,
		arguments = listOf(
			navArgument("id") { type = NavType.IntType },
			navArgument("type") { type = NavType.StringType }
		)
	) {
		val viewModel = hiltViewModel<DetailInformationViewModel>()
		val uiState by viewModel.detailInformation.collectAsStateWithLifecycle()


		val id = requireNotNull(it.arguments?.getInt("id"))
		val type = remember {
			TypeDetailInformation.valueOf(requireNotNull(it.arguments?.getString("type")))
		}

		LaunchedEffect(Unit) {
			viewModel.getDetailInformation(id, type)
		}

		DetailInformationScreen(
			uiState = uiState,
			type = type,
			onClickBack = onClickBack
		)
	}
}