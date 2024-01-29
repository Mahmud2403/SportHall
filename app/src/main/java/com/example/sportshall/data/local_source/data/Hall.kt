package com.example.sportshall.data.local_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Hall(
	val id: Int = 1,
	val typeOfSport: String
)

val hallList = listOf(
	Hall(
		id = 1,
		typeOfSport = typeOfSportList[0].typeOfOccupation
	),
	Hall(
		id = 2,
		typeOfSport = typeOfSportList[4].typeOfOccupation
	),
	Hall(
		id = 3,
		typeOfSport = typeOfSportList[2].typeOfOccupation
	),
	Hall(
		id = 4,
		typeOfSport = typeOfSportList[1].typeOfOccupation
	),
	Hall(
		id = 5,
		typeOfSport = typeOfSportList[3].typeOfOccupation
	),
	Hall(
		id = 6,
		typeOfSport = typeOfSportList[3].typeOfOccupation
	),
	Hall(
		id = 7,
		typeOfSport = typeOfSportList[1].typeOfOccupation
	),
	Hall(
		id = 8,
		typeOfSport = typeOfSportList[4].typeOfOccupation
	),
	Hall(
		id = 9,
		typeOfSport = typeOfSportList[2].typeOfOccupation
	),
	Hall(
		id = 10,
		typeOfSport = typeOfSportList[0].typeOfOccupation
	),

)
