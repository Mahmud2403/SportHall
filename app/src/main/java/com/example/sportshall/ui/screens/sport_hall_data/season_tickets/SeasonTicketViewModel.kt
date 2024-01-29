package com.example.sportshall.ui.screens.sport_hall_data.season_tickets

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.data.local_source.event.SeasonTicketEvent
import com.example.sportshall.domain.repository.SeasonTicketRepository
import com.example.sportshall.domain.use_case.InsertSeasonTicketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonTicketViewModel@Inject constructor(
	@ApplicationContext private val context: Context,
	private val insertSeasonTicketUseCase: InsertSeasonTicketUseCase,
	private val repo: SeasonTicketRepository
) : ViewModel() {

	private var _seasonTicketsUiState = MutableStateFlow(SeasonTicketUiState())
	val seasonTicketsUiState = _seasonTicketsUiState.asStateFlow()
	init {
		viewModelScope.launch {
			repo.getListSeasonTicket().collect { newSeasonTicket ->
				_seasonTicketsUiState.update { currentState ->
					Log.i("AAAA", "$newSeasonTicket")
					currentState.copy(
						seasonTicketEntities = newSeasonTicket
					)
				}
			}
		}
	}
	fun deleteSeasonTicket(seasonTicketsEntity: SeasonTicketEntity) {
		viewModelScope.launch {
			repo.deleteSeasonTicket(seasonTicketsEntity)
		}
	}

	fun addSeasonTicket(seasonTickets: SeasonTicketEvent) {
		when (seasonTickets) {
			SeasonTicketEvent.HideDialog -> {
				_seasonTicketsUiState.update {
					it.copy(
						isAddingClients = false
					)
				}
			}

			SeasonTicketEvent.SaveSeasonTicketEvent -> {
				val id = seasonTicketsUiState.value.id
				val term = seasonTicketsUiState.value.term
				val nameClient = seasonTicketsUiState.value.nameClient
				val nameTrainer = seasonTicketsUiState.value.nameTrainer
				val change = seasonTicketsUiState.value.change

				if (term.isBlank() || nameClient.isBlank() || nameTrainer.isBlank() || change.isBlank()) {
					return
				}

				val seasonTicketEntity = SeasonTicketEntity(
					id = id,
					nameClient = nameClient,
					term = term,
					nameTrainer = nameTrainer,
					change = change,
				)

				viewModelScope.launch {
					insertSeasonTicketUseCase(seasonTicketEntity)
				}

				_seasonTicketsUiState.update {
					it.copy(
						isAddingClients = false,

					)
				}
			}

			is SeasonTicketEvent.SetIdClient -> {
				_seasonTicketsUiState.update {
					it.copy(
						nameClient = seasonTickets.nameClient
					)
				}
			}

			is SeasonTicketEvent.SetTerm -> {
				_seasonTicketsUiState.update {
					it.copy(
						term = seasonTickets.term
					)
				}
			}

			is SeasonTicketEvent.SetIdTrainer -> {
				_seasonTicketsUiState.update {
					it.copy(
						nameTrainer = seasonTickets.nameTrainer
					)
				}
			}

			is SeasonTicketEvent.SetChange -> {
				_seasonTicketsUiState.update {
					it.copy(
						change = seasonTickets.change
					)
				}
			}
			SeasonTicketEvent.ShowDialog -> {
				_seasonTicketsUiState.update {
					it.copy(
						isAddingClients = true
					)
				}
			}
		}
	}

	fun generateRandomSeasonTicket() {
		_seasonTicketsUiState.update { currentState ->
			currentState.copy(
				term = "20",
				nameClient = "1",
				nameTrainer = "3",
				change = Change.Afternoon.text,
			)
		}
	}
}

data class SeasonTicketUiState(
	val seasonTicketEntities: List<SeasonTicketEntity> = emptyList(),
	val id: Int = 0,
	val term: String = "",
	var nameClient: String = "",
	var nameTrainer: String = "",
	var change: String = "",
	val isAddingClients: Boolean = false,
)

enum class Change(val timeStart: String, val text: String){
	Morning("10:00", "Утренняя"),
	Afternoon("14:00", "Обеденная"),
	Evening("18:00", "Вечерняя")
}