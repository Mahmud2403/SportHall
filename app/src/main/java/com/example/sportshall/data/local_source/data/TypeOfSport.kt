package com.example.sportshall.data.local_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TypeOfSport(
	val id: Int = 0,
	val typeOfOccupation: String = "",
	val price: String = "",
)

val typeOfSportList = listOf(
	TypeOfSport(
		id = 1,
		typeOfOccupation = "Кикбоксинг",
		price = "400 ₽"
	),
	TypeOfSport(
		id = 2,
		typeOfOccupation = "Джиу-Джитсу",
		price = "350 ₽"
	),
	TypeOfSport(
		id = 3,
		typeOfOccupation = "Вольная борьба",
		price = "300 ₽"
	),
	TypeOfSport(
		id = 4,
		typeOfOccupation = "Карате",
		price = "500 ₽"
	),
	TypeOfSport(
		id = 5,
		typeOfOccupation = "Рукопашные бои",
		price = "450 ₽"
	),

)
