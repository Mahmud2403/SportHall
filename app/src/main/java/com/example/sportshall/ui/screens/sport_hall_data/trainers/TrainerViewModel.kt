package com.example.sportshall.ui.screens.sport_hall_data.trainers

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.data.local_source.event.TrainerEvent
import com.example.sportshall.domain.repository.TrainerRepository
import com.example.sportshall.domain.use_case.InsertTrainersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainerViewModel @Inject constructor(
	@ApplicationContext private val context: Context,
	private val repo: TrainerRepository,
	private val insertTrainersUseCase: InsertTrainersUseCase
) : ViewModel() {

	private var _trainersUiState = MutableStateFlow(TrainerUiState())
	val trainersUiState = _trainersUiState.asStateFlow()
	init {
		viewModelScope.launch {
			repo.getListTrainers().collect { newClients ->
				_trainersUiState.update { currentState ->
					Log.i("AAAA", "$newClients")
					currentState.copy(
						trainerEntities = newClients
					)
				}
			}
		}
	}
	fun getClient(id: Int) = viewModelScope.launch {
	}

	fun deleteTrainer(trainerEntity: TrainerEntity) {
		viewModelScope.launch {
			repo.deleteTrainer(trainerEntity)
		}
	}

	fun addTrainer(trainerEvent: TrainerEvent) {
		when (trainerEvent) {
			TrainerEvent.HideDialog -> {
				_trainersUiState.update {
					it.copy(
						isAddingTrainer = false
					)
				}
			}

			TrainerEvent.SaveTrainer -> {
				val id = trainersUiState.value.id
				val firstName = trainersUiState.value.firstName
				val lastName = trainersUiState.value.lastName
				val patronymic = trainersUiState.value.patronymic
				val phoneNumber = trainersUiState.value.phoneNumber
				val workExperience = trainersUiState.value.workExperience
				val typeOfOccupation = trainersUiState.value.typeOfOccupation

				if (firstName.isBlank() || lastName.isBlank() || patronymic.isBlank() || phoneNumber.isBlank() || typeOfOccupation.isBlank()) {
					return
				}

				val trainerEntity = TrainerEntity(
					id = id,
					firstName = firstName,
					lastName = lastName,
					patronymic = patronymic,
					phoneNumber = phoneNumber,
					typeOfSport = typeOfOccupation,
					workExperience = workExperience,
				)

				viewModelScope.launch {
					insertTrainersUseCase(trainerEntity = trainerEntity)
				}

				_trainersUiState.update {
					it.copy(
						isAddingTrainer = false,
						firstName = "",
						lastName = "",
						patronymic = "",
						phoneNumber = "",
						typeOfOccupation = "",
						workExperience = "",
					)
				}
			}

			is TrainerEvent.SetFirstName -> {
				_trainersUiState.update {
					it.copy(
						firstName = trainerEvent.firstName
					)
				}
			}

			is TrainerEvent.SetLastName -> {
				_trainersUiState.update {
					it.copy(
						lastName = trainerEvent.lastName
					)
				}
			}

			is TrainerEvent.SetPatronymic -> {
				_trainersUiState.update {
					it.copy(
						patronymic = trainerEvent.patronymic
					)
				}
			}

			is TrainerEvent.SetPhoneNumber -> {
				_trainersUiState.update {
					it.copy(
						phoneNumber = trainerEvent.phoneNumber
					)
				}
			}

			is TrainerEvent.SetTypeOfOccupation -> {
				_trainersUiState.update {
					it.copy(
						typeOfOccupation = trainerEvent.typeOfOccupation
					)
				}
			}

			is TrainerEvent.SetWorkExperience -> {
				_trainersUiState.update {
					it.copy(
						workExperience = trainerEvent.workExperience
					)
				}
			}

			TrainerEvent.ShowDialog -> {
				_trainersUiState.update {
					it.copy(
						isAddingTrainer = true
					)
				}
			}
		}
	}

	fun generateRandomTrainer() {
		_trainersUiState.update { currentState ->
			currentState.copy(
				firstName = "Фамилия",
				lastName = "Имя",
				patronymic = "Отчество",
				phoneNumber = "9438748390",
				workExperience = "4",
				typeOfOccupation = ""
			)
		}
	}

}

data class TrainerUiState(
	val trainerEntities: List<TrainerEntity> = emptyList(),
	var id: Int = 0,
	val firstName: String = "",
	val lastName: String = "",
	val patronymic: String = "",
	val phoneNumber: String = "",
	val workExperience: String = "",
	var typeOfOccupation: String = "",
	val isAddingTrainer: Boolean = false,
)