package com.example.sportshall.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.data.local_source.data.TypeOfSport
import com.example.sportshall.domain.repository.FilterRepository
import com.example.sportshall.ui.screens.sport_hall_data.clients.Gender
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.Change
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val filterRepository: FilterRepository,
) : ViewModel() {

	private val _homeUiState = MutableStateFlow(HomeUiState())
	val homeUiState = _homeUiState.asStateFlow()

	private val _filterClientsByBirthday = MutableStateFlow<List<ClientEntity>>(emptyList())
	val filterClientsByBirthday = _filterClientsByBirthday.asStateFlow()

	private val _filterTrainerByTypeOfSport = MutableStateFlow<List<TrainerEntity>>(emptyList())
	val filterTrainerByTypeOfSport = _filterTrainerByTypeOfSport.asStateFlow()

	private val _filterClientsByGender = MutableStateFlow<List<ClientEntity>>(emptyList())
	val filterClientsByGender = _filterClientsByGender.asStateFlow()

	private val _filterSeasonTicketChange = MutableStateFlow<List<SeasonTicketEntity>>(emptyList())
	val filterSeasonTicketChange = _filterSeasonTicketChange.asStateFlow()

	fun syncFilter() {
		viewModelScope.launch {
			val newDataBirthday = filterRepository.filteringByBirthday(
				_homeUiState.value.birthDay.startDate,
				_homeUiState.value.birthDay.endData,
			)
			val newDataGender = filterRepository.filterByGender(
				_homeUiState.value.gender,
			)
			val newDataSport = filterRepository.filterByTypeOfSport(
				_homeUiState.value.typeOfSport
			)
			val newDataChange = filterRepository.filterByChange(
				_homeUiState.value.changeType
			)
			val combinedData: List<ClientEntity> = when {
				_homeUiState.value.birthDay.isFilterSet() && _homeUiState.value.gender.isNotEmpty() -> {
					newDataBirthday.filter { it.gender == _homeUiState.value.gender }
				}

				_homeUiState.value.birthDay.isFilterSet() -> {
					// Если установлен только фильтр по дате рождения
					newDataBirthday
				}

				_homeUiState.value.gender.isNotEmpty() -> {
					// Если установлен только фильтр по полу
					newDataGender
				}

				else -> {
					newDataBirthday + newDataGender
				}
			}
			_filterClientsByBirthday.update {
				newDataBirthday
			}
			_filterClientsByGender.update {
				newDataGender
			}
			_filterTrainerByTypeOfSport.update {
				newDataSport
			}
			_filterSeasonTicketChange.update {
				newDataChange
			}

			_homeUiState.value.trainerEntities = newDataSport
			_homeUiState.value.clientEntities = combinedData
			_homeUiState.value.seasonTicketEntity = newDataChange
		}
	}

	fun changeDate(date: Long, dateType: Date) {
		_homeUiState.update { currentState ->
			currentState.copy(
				birthDay = currentState.birthDay.copy(
					startDate = if (dateType == Date.Start) date else currentState.birthDay.startDate,
					endData = if (dateType == Date.End) date else currentState.birthDay.endData,
				)
			)
		}
	}
}

data class HomeUiState(
	var clientEntities: List<ClientEntity> = emptyList(),
	var trainerEntities: List<TrainerEntity> = emptyList(),
	var seasonTicketEntity: List<SeasonTicketEntity> = emptyList(),
	var changeType: String = "",
	val birthDay: DateRange = DateRange(),
	var gender: String = "",
	var typeOfSport: String = "",
)

data class DateRange(
	val startDate: Long? = null,
	val endData: Long? = null,
){
	fun isFilterSet(): Boolean{
		return startDate != null && endData != null

	}
}

enum class Date {
	Start, End
}