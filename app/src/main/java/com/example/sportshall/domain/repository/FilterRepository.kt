package com.example.sportshall.domain.repository

import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import kotlinx.coroutines.flow.Flow

interface FilterRepository {
	suspend fun filteringByBirthday(startDate: Long?, endDate: Long?): List<ClientEntity>

	suspend fun filterByTypeOfSport(typeOfSport: String): List<TrainerEntity>

	suspend fun filterByGender(gender: String): List<ClientEntity>

	suspend fun filterByChange(change: String): List<SeasonTicketEntity>
}