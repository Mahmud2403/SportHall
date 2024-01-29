package com.example.sportshall.domain.use_case

import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.domain.repository.SeasonTicketRepository
import javax.inject.Inject

class InsertSeasonTicketUseCase@Inject constructor(
	private val repo: SeasonTicketRepository
){
	suspend operator fun invoke(seasonTicketEntity: SeasonTicketEntity) = repo.insertSeasonTicket(seasonTicketEntity)
}