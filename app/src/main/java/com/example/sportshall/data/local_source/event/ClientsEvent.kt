package com.example.sportshall.data.local_source.event

import com.example.sportshall.ui.screens.sport_hall_data.clients.Gender

sealed interface ClientsEvent{
	object SaveClient: ClientsEvent
	object ShowDialog: ClientsEvent
	object HideDialog: ClientsEvent
	data class SetFirstName(val firstName: String): ClientsEvent
	data class SetLastName(val lastName: String ): ClientsEvent
	data class SetPatronymic(val patronymic: String ): ClientsEvent
	data class SetPhoneNumber(val phoneNumber: String ): ClientsEvent
	data class SetBirthDay(val birthDay: Long): ClientsEvent
	data class SetGender(val gender: Gender): ClientsEvent
}