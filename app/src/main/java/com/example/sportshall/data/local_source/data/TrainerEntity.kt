package com.example.sportshall.data.local_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportshall.ui.screens.search.model.SportHallInformation.TrainerInformation

@Entity(tableName = "trainer")
data class TrainerEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 1,
	val firstName: String,
	val lastName: String,
	val patronymic: String,
	val phoneNumber: String,
	val workExperience: String,
	val typeOfSport: String,
)

fun TrainerEntity.toTrainer() = TrainerInformation(
	id = id,
	firstName = firstName,
	lastName = lastName,
	patronymic = patronymic,
	phoneNumber = phoneNumber,
	workExperience = workExperience,
	typeOfSport = typeOfSport,
)