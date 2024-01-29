package com.example.sportshall.domain.repository

import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import kotlinx.coroutines.flow.Flow

interface DetailInformationRepository {
	fun getDetailInformationClient(id: Int): Flow<ClientEntity>

	fun getDetailInformationTrainer(id: Int): Flow<TrainerEntity>

	fun getDetailInformationSeasonTicket(id: Int): Flow<SeasonTicketEntity>

}