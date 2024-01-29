package com.example.sportshall.data.repository

import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.domain.repository.TrainerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainerRepositoryImpl @Inject constructor(
	private val dao: SportHallDao,
): TrainerRepository {
	override suspend fun insertTrainers(trainerEntity: TrainerEntity) {
		withContext(Dispatchers.IO) {
			dao.upsertTrainer(trainerEntity)
		}
	}
	override fun getListTrainers() = dao.getTrainers()
	override suspend fun deleteTrainer(trainerEntity: TrainerEntity) {
		withContext(Dispatchers.IO) {
			dao.deleteTrainer(trainerEntity)
		}
	}
}