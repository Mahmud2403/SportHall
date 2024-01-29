package com.example.sportshall.ui.screens.sport_hall_data.detail_information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshall.domain.repository.DetailInformationRepository
import com.example.sportshall.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInformationViewModel @Inject constructor(
	private val detailInformationRepository: DetailInformationRepository
) : ViewModel() {

	private val _detailInformation = MutableStateFlow(DetailInformationUiState())
	val detailInformation = _detailInformation.asStateFlow()

	fun getDetailInformation(id: Int, type: TypeDetailInformation) {
		viewModelScope.launch {
			when (type) {
				TypeDetailInformation.Client -> getDetailInformationClient(id)
				TypeDetailInformation.Trainer -> getDetailInformationTrainer(id)
				TypeDetailInformation.SeasonTicket -> getDetailInformationSeasonTicket(id)
			}
		}
	}

	private suspend fun getDetailInformationClient(id: Int) {
		detailInformationRepository.getDetailInformationClient(id).collect { client ->
			_detailInformation.update { currentState ->
				currentState.copy(
					firstName = client.firstName,
					lastName = client.lastName,
					patronymic = client.patronymic,
					phoneNumber = client.phoneNumber,
					birthDay = client.birthDay,
					gender = client.gender,
				)
			}
		}
	}

	private suspend fun getDetailInformationTrainer(id: Int) {
		detailInformationRepository.getDetailInformationTrainer(id).collect { trainer ->
			_detailInformation.update { currentState ->
				currentState.copy(
					firstName = trainer.firstName,
					lastName = trainer.lastName,
					patronymic = trainer.patronymic,
					phoneNumber = trainer.phoneNumber,
					workExperience = trainer.workExperience,
					typeOfOccupation = trainer.typeOfSport,
				)
			}
		}
	}

	private suspend fun getDetailInformationSeasonTicket(id: Int) {
		detailInformationRepository.getDetailInformationSeasonTicket(id).collect { seasonTicket ->
			_detailInformation.update { currentState ->
				currentState.copy(
					change = seasonTicket.change,
					nameTrainer = seasonTicket.nameTrainer,
					term = seasonTicket.term,
					nameClient = seasonTicket.nameClient,
				)
			}
		}
	}

}

data class DetailInformationUiState(
	val firstName: String = "",
	val lastName: String = "",
	val patronymic: String = "",
	val phoneNumber: String = "",
	val birthDay: Long? = null,
	val gender: String = "",
	val term: String = "",
	val nameClient: String = "",
	val nameTrainer: String = "",
	val change: String = "",
	val workExperience: String = "",
	val typeOfOccupation: String = "",
)

enum class TypeDetailInformation {
	Client,
	Trainer,
	SeasonTicket,
}