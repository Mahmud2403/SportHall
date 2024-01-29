package com.example.sportshall.data.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.SearchData
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.data.local_source.data.TrainerEntity


@Database(
	entities = [ClientEntity::class, SeasonTicketEntity::class, TrainerEntity::class, SearchData::class],
	version = 9
)
abstract class SportHallDataBase : RoomDatabase() {
	abstract val dao: SportHallDao
}