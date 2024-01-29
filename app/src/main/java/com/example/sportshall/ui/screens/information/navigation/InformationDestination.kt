package com.example.sportshall.ui.screens.information.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sportshall.base.navigation.SportHallNavigationDestination
import com.example.sportshall.ui.screens.information.InformationScreen
import com.example.sportshall.ui.screens.sport_hall_data.clients.navigation.AddClientsDestination
import com.example.sportshall.ui.screens.sport_hall_data.clients.navigation.ViewClientsDestination
import com.example.sportshall.ui.screens.sport_hall_data.halls.navigation.HallDestination
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.navigation.AddSeasonTicketNavigation
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.navigation.ViewSeasonTicketNavigation
import com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation.AddTrainerDestination
import com.example.sportshall.ui.screens.sport_hall_data.trainers.navigation.ViewTrainerDestination
import com.example.sportshall.ui.screens.sport_hall_data.type_of_sport.navigation.TypeOfSportDestination

object InformationDestination : SportHallNavigationDestination {
	override val route = "information_route"
	override val destination = "information_destination"
}

fun NavGraphBuilder.informationGraph(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit,
	nestedGraphs: NavGraphBuilder.() -> Unit,
) {
	navigation(
		route = InformationDestination.route,
		startDestination = InformationDestination.destination,
	) {
		nestedGraphs()
	}
}

fun NavGraphBuilder.information(
	navigateTo: (destination: SportHallNavigationDestination, route: String?) -> Unit
) {
	composable(route = InformationDestination.destination) {
		InformationScreen(
			onClickAddClients = { navigateTo(AddClientsDestination, null) },
			onClickViewClients = { navigateTo(ViewClientsDestination, null) },
			onClickAddTrainer = { navigateTo(AddTrainerDestination, null) },
			onClickViewTrainer = { navigateTo(ViewTrainerDestination, null) },
			onClickViewTypeOfSport = { navigateTo(TypeOfSportDestination, null) },
			onClickViewSeasonTicket = { navigateTo(ViewSeasonTicketNavigation, null) },
			onClickViewHalls = { navigateTo(HallDestination, null) },
			onClickAddSeasonTicket = { navigateTo(AddSeasonTicketNavigation, null) }
		)
	}
}