package com.example.sportshall.data.local_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportshall.ui.screens.search.model.SportHallInformation
import com.example.sportshall.ui.screens.search.model.SportHallInformation.ClientInformation

@Entity(tableName = "clientEntities")
data class ClientEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 1,
	val firstName: String,
	val lastName: String,
	val patronymic: String,
	val phoneNumber: String,
	val birthDay: Long?,
	val gender: String,
)

fun ClientEntity.toClient() = ClientInformation(
	id = id,
	firstName = firstName,
	lastName = lastName,
	patronymic = patronymic,
	phoneNumber = phoneNumber,
	birthDay = birthDay,
	gender = gender,
)
