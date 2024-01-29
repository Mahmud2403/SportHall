package com.example.sportshall.data.local_source.data

data class DetailInformation(
	val firstName: String,
	val lastName: String,
	val patronymic: String,
	val phoneNumber: String,
	val birthDay: String,
	val gender: String,
	val term: String,
	val idClient: Int,
	val idTrainer: Int,
	val change: String,
	val workExperience: String,
	val typeOfOccupation: String,
)