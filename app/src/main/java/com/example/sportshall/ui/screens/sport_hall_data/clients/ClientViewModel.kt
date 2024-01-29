package com.example.sportshall.ui.screens.sport_hall_data.clients

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshall.data.local_source.event.ClientsEvent
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.domain.repository.ClientRepository
import com.example.sportshall.domain.use_case.InsertClientsUseCase
import com.example.sportshall.utils.Converter
import com.example.sportshall.utils.currentLocale
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
	@ApplicationContext private val context: Context,
	private val insertClientsUseCase: InsertClientsUseCase,
	private val clientRepository: ClientRepository
) : ViewModel() {

	private var _clientsUiState = MutableStateFlow(ClientUiState())
	val clientsUiState = _clientsUiState.asStateFlow()
	init {
		viewModelScope.launch {
			clientRepository.getListClients().collect { newClients ->
				_clientsUiState.update { currentState ->
					Log.i("AAAA", "$newClients")
					currentState.copy(
						clientEntities = newClients
					)
				}
			}
		}
	}
	fun getClient(id: Int) = viewModelScope.launch {
	}

	fun deleteClient(clients: ClientEntity) {
		viewModelScope.launch {
			clientRepository.deleteClient(clients)
		}
	}

	fun addClients(clients: ClientsEvent) {
		when (clients) {
			ClientsEvent.HideDialog -> {
				_clientsUiState.update {
					it.copy(
						isAddingClients = false
					)
				}
			}

			ClientsEvent.SaveClient -> {
				val id = clientsUiState.value.id
				val firstName = clientsUiState.value.firstName
				val lastName = clientsUiState.value.lastName
				val patronymic = clientsUiState.value.patronymic
				val gender = clientsUiState.value.gender
				val phoneNumber = clientsUiState.value.phoneNumber
				val birthDay = clientsUiState.value.date

				if (firstName.isBlank() || lastName.isBlank() || patronymic.isBlank() || phoneNumber.isBlank()) {
					return
				}

				val clientEntity = ClientEntity(
					id = id,
					firstName = firstName,
					lastName = lastName,
					patronymic = patronymic,
					gender = gender,
					phoneNumber = phoneNumber,
					birthDay = birthDay
				)

				viewModelScope.launch {
					insertClientsUseCase(clientEntity)
					Log.e("addclients", "$clientEntity")
				}

				_clientsUiState.update {
					it.copy(
						isAddingClients = false,
						firstName = "",
						lastName = "",
						patronymic = "",
						date = 0,
						phoneNumber = "",
						gender = "",
					)
				}
			}

			is ClientsEvent.SetBirthDay -> {
				_clientsUiState.update {
					it.copy(
						date = clients.birthDay
					)
				}
			}

			is ClientsEvent.SetFirstName -> {
				_clientsUiState.update {
					it.copy(
						firstName = clients.firstName
					)
				}
			}

			is ClientsEvent.SetGender -> {
				_clientsUiState.update {
					it.copy(
						gender = clients.gender.name
					)
				}
			}

			is ClientsEvent.SetLastName -> {
				_clientsUiState.update {
					it.copy(
						lastName = clients.lastName
					)
				}
			}

			is ClientsEvent.SetPatronymic -> {
				_clientsUiState.update {
					it.copy(
						patronymic = clients.patronymic
					)
				}
			}

			is ClientsEvent.SetPhoneNumber -> {
				_clientsUiState.update {
					it.copy(
						phoneNumber = clients.phoneNumber
					)
				}
			}

			ClientsEvent.ShowDialog -> {
				_clientsUiState.update {
					it.copy(
						isAddingClients = true
					)
				}
			}
		}
	}


	fun changeDate(date: Long) {
		_clientsUiState.update { currentState ->
			currentState.copy(
				date = date
			)
		}
	}

	fun generateRandomClient() {
		_clientsUiState.update { currentState ->
			currentState.copy(
				firstName = "Фамилия",
				lastName = "Имя",
				patronymic = "Отчество",
				phoneNumber = "9438748390",
				gender = Gender.Woman.name,
				date = 1016658000000,
			)
		}
	}


}

data class ClientUiState(
	val clientEntities: List<ClientEntity> = emptyList(),
	var id: Int = 0,
	val firstName: String = "",
	val lastName: String = "",
	val patronymic: String = "",
	val phoneNumber: String = "",
	var gender: String = "",
	val date: Long? = null,
	val isAddingClients: Boolean = false,
)

enum class Gender(val value: String) {
	Man("Мужской"),
	Woman("Женский")
}


