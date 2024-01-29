package com.example.sportshall.data.repository

import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.domain.repository.SeasonTicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeasonTicketRepositoryImpl @Inject constructor(
	private val dao: SportHallDao,
) : SeasonTicketRepository {
	override suspend fun insertSeasonTicket(seasonTicketEntity: SeasonTicketEntity) {
		withContext(Dispatchers.IO) {
			dao.insertSeasonTicket(seasonTicketEntity)
		}
	}
	override suspend fun deleteSeasonTicket(seasonTicketEntity: SeasonTicketEntity) {
		withContext(Dispatchers.IO) {
			dao.deleteSeasonTicket(seasonTicketEntity)
		}
	}
	override fun getListSeasonTicket(): Flow<List<SeasonTicketEntity>>  = dao.getListSeasonTicket()
}