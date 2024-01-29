package com.example.sportshall.domain.repository

import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import kotlinx.coroutines.flow.Flow

interface SeasonTicketRepository {
	suspend fun insertSeasonTicket(seasonTicketEntity: SeasonTicketEntity)
	suspend fun deleteSeasonTicket(seasonTicketEntity: SeasonTicketEntity)
	fun getListSeasonTicket(): Flow<List<SeasonTicketEntity>>
}