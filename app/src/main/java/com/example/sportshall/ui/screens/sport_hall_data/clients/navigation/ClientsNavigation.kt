package com.example.sportshall.ui.screens.sport_hall_data.clients.navigation

import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.data.local_source.event.ClientsEvent
import com.example.sportshall.ui.screens.information.navigation.InformationDestination
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientViewModel
import com.example.sportshall.ui.screens.sport_hall_data.clients.add_clients.AddClientsScreen
import com.example.sportshall.ui.screens.sport_hall_data.clients.view_clients.ViewClientsScreen
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.TypeDetailInformation
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.navigation.DetailInformationNavigation
import com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation.ViewTrainerDestination

object AddClientsDestination : SportHallNavigationDestination {
	override val route = "add_clients_route"
	override val destination = "add_clients_destination"
}
object ViewClientsDestination : SportHallNavigationDestination {
	override val route = "view_clients_route"
	override val destination = "view_clients_destination"
}

fun NavGraphBuilder.client(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	onClickBack: () -> Unit
) {

	composable(route = AddClientsDestination.route) {
		val context = LocalContext.current
		val viewModel: ClientViewModel = hiltViewModel()
		val uiState by viewModel.clientsUiState.collectAsState()

		AddClientsScreen(
			uiState = uiState,
			onClickBack = onClickBack,
			onClickAdd = {
				viewModel.addClients(
					ClientsEvent.SaveClient
				)
				navigateTo(InformationDestination, null)
				Toast.makeText(context, "ClientInformation added", Toast.LENGTH_LONG).show()
			},
			onClients = viewModel::addClients,
			onChangeDate = { date ->
				viewModel.changeDate(
					date = date,
				)
			},
			onClickRandom = viewModel::generateRandomClient
		)
	}
	composable(route = ViewClientsDestination.route){
		val viewModel = hiltViewModel<ClientViewModel>()
		val uiState by viewModel.clientsUiState.collectAsState()

		ViewClientsScreen(
			state = uiState,
			onClickBack = onClickBack,
			onDelete = viewModel::deleteClient,
			onClickClient = { id ->
				navigateTo(
					ViewClientsDestination,
					DetailInformationNavigation.navigateWithArgument(id, TypeDetailInformation.Client.toString())
				)
			}
		)
	}
}