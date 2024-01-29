package com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation

import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.data.local_source.event.TrainerEvent
import com.example.sportshall.ui.screens.information.navigation.InformationDestination
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.TypeDetailInformation
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation.DetailInformationNavigation
import com.example.sportshall.ui.screens.sport_hall_data.trainers.TrainerViewModel
import com.example.sportshall.ui.screens.sport_hall_data.trainers.add_trainer.AddTrainerScreen
import com.example.sportshall.ui.screens.sport_hall_data.trainers.view_trainer.ViewTrainerScreen

object AddTrainerDestination : SportHallNavigationDestination {
	override val route = "add_trainer_route"
	override val destination = "add_trainer_destination"
}
object ViewTrainerDestination : SportHallNavigationDestination {
	override val route = "view_trainer_route"
	override val destination = "view_trainer_destination"
}


fun NavGraphBuilder.trainer(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit
) {

	composable(route = AddTrainerDestination.route) {
		val context = LocalContext.current
		val viewModel: TrainerViewModel = hiltViewModel()
		val uiState by viewModel.trainersUiState.collectAsState()

		AddTrainerScreen(
			uiState = uiState,
			onClickBack = onClickBack,
			onClickAdd = {
				viewModel.addTrainer(
					TrainerEvent.SaveTrainer
				)
				navigateTo(InformationDestination, null)
				Toast.makeText(context, "ClientInformation added", Toast.LENGTH_LONG).show()
			},
			onTrainer = viewModel::addTrainer,
			onClickRandom = viewModel::generateRandomTrainer
		)
	}
	composable(route = ViewTrainerDestination.route){
		val viewModel: TrainerViewModel = hiltViewModel()
		val uiState by viewModel.trainersUiState.collectAsState()

		ViewTrainerScreen(
			state = uiState,
			onClickBack = onClickBack,
			onDelete = viewModel::deleteTrainer,
			onClickTrainer = { id ->
				navigateTo(
					ViewTrainerDestination,
					DetailInformationNavigation.navigateWithArgument(id, TypeDetailInformation.Trainer.toString())
				)
			},
		)
	}
}