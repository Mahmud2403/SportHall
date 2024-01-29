package com.example.sportshall.domain.use_case

import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.domain.repository.TrainerRepository
import javax.inject.Inject

class InsertTrainersUseCase @Inject constructor(
	private val repo: TrainerRepository
){
	suspend operator fun invoke(trainerEntity: TrainerEntity) = repo.insertTrainers(trainerEntity)
}