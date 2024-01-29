package com.example.sportshall.ui.screens.search.model

sealed interface SportHallInformation{
	val id: Int
	val firstName: String
	val lastName: String
	val patronymic: String
	val phoneNumber: String

	data class ClientInformation(
		override val id: Int,
		override val firstName: String,
		override val lastName: String,
		override val patronymic: String,
		override val phoneNumber: String,
		val birthDay: Long?,
		var gender: String,
	): SportHallInformation {
		companion object {
			val mock = ClientInformation(
				id = 1,
				firstName = "Имя",
				lastName = "Фамилия",
				patronymic = "Отчество",
				phoneNumber = "+7 (999) 764 45 23",
				birthDay = 1016658000000,
				gender = "Man",
			)
		}
	}

	data class TrainerInformation(
		override val id: Int,
		override val firstName: String,
		override val lastName: String,
		override val patronymic: String,
		override val phoneNumber: String,
		val workExperience: String,
		var typeOfSport: String
	): SportHallInformation {
		companion object {
			val mock = TrainerInformation(
				id = 1,
				firstName = "Имя",
				lastName = "Фамилия",
				patronymic = "Отчество",
				phoneNumber = "+7 (999) 764 45 23",
				workExperience = "3",
				typeOfSport = "Карате"
			)
		}
	}
}