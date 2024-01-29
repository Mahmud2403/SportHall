package com.example.sportshall.data.repository

import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.domain.repository.DetailInformationRepository
import javax.inject.Inject

class DetailInformationRepositoryImpl @Inject constructor(
	private val dao: SportHallDao
): DetailInformationRepository {
	override fun getDetailInformationClient(id: Int) = dao.getDetailInformationClient(id)
	override fun getDetailInformationTrainer(id: Int) = dao.getDetailInformationTrainer(id)
	override fun getDetailInformationSeasonTicket(id: Int) = dao.getDetailInformationSeasonTicket(id)

}