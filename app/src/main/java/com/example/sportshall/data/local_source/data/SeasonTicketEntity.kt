package com.example.sportshall.data.local_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seasonticket")
data class SeasonTicketEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 1,
	val term: String,
	val nameClient: String,
	val nameTrainer: String,
	val change: String,
)
