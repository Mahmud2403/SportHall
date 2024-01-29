package com.example.sportshall.domain.repository

import com.example.sportshall.data.local_source.data.TrainerEntity
import kotlinx.coroutines.flow.Flow


interface TrainerRepository{
	suspend fun insertTrainers(trainerEntity: TrainerEntity)
	suspend fun deleteTrainer(trainerEntity: TrainerEntity)
	fun getListTrainers(): Flow<List<TrainerEntity>>
}