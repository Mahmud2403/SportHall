package com.example.sportshall.ui.screens.sport_hall_data.season_tickets.navigation

import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.data.local_source.event.SeasonTicketEvent
import com.example.sportshall.ui.screens.information.navigation.InformationDestination
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientUiState
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientViewModel
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.TypeDetailInformation
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation.DetailInformationNavigation
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.SeasonTicketViewModel
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.add.AddSeasonTicketScreen
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.view.ViewSeasonTicketScreen
import com.example.sportshall.ui.screens.sport_hall_data.trainers.TrainerUiState
import com.example.sportshall.ui.screens.sport_hall_data.trainers.TrainerViewModel

object AddSeasonTicketNavigation : SportHallNavigationDestination {
	override val route = "add_season_ticket_route"
	override val destination = "add_season_ticket_destination"
}

object ViewSeasonTicketNavigation : SportHallNavigationDestination {
	override val route = "view_season_ticket_route"
	override val destination = "view_season_ticket_destination"
}

fun NavGraphBuilder.seasonTicket(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit
) {
	composable(AddSeasonTicketNavigation.route) {
		val viewModelSeasonTicket = hiltViewModel<SeasonTicketViewModel>()
		val uiStateSeasonTicket by viewModelSeasonTicket.seasonTicketsUiState.collectAsState()

		val viewModelTrainer = hiltViewModel<TrainerViewModel>()
		val uiStateTrainer by viewModelTrainer.trainersUiState.collectAsStateWithLifecycle()

		val viewModelClient = hiltViewModel<ClientViewModel>()
		val uiStateClient by viewModelClient.clientsUiState.collectAsStateWithLifecycle()

		val context = LocalContext.current

		AddSeasonTicketScreen(
			uiState = uiStateSeasonTicket,
			onClickBack = onClickBack,
			onClickAdd = {
				viewModelSeasonTicket.addSeasonTicket(
					SeasonTicketEvent.SaveSeasonTicketEvent
				)
				navigateTo(InformationDestination, null)
				Toast.makeText(context, "Season ticket added", Toast.LENGTH_LONG).show()
			},
			onSeasonTicket = viewModelSeasonTicket::addSeasonTicket,
			onClickRandom = { /*TODO*/ },
			clientUiState = uiStateClient,
			trainerUiState = uiStateTrainer,
		)
	}

	composable(ViewSeasonTicketNavigation.route){
		val viewModel = hiltViewModel<SeasonTicketViewModel>()
		val uiState by viewModel.seasonTicketsUiState.collectAsStateWithLifecycle()

		ViewSeasonTicketScreen(
			state = uiState,
			onClickBack = onClickBack,
//			onDelete = ,
			onClickTrainer = { id ->
				navigateTo(
					ViewSeasonTicketNavigation,
					DetailInformationNavigation.navigateWithArgument(id, TypeDetailInformation.SeasonTicket.toString())
				)
			}
		)
	}



}