package com.example.sportshall.data.local_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
data class SearchData(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val text: String
)

//fun SearchData.toSearchHistory() =
//	SearchData(text = text)