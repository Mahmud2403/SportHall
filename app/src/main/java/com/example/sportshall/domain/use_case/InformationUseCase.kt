package com.example.sportshall.domain.use_case

import com.example.sportshall.domain.repository.FilterRepository
import javax.inject.Inject

class InformationUseCase @Inject constructor(
	private val repo: FilterRepository
){
//	suspend operator fun invoke(
//		startDate: Long? = null,
//		endDate: Long? = null,
//		typeOfSport: String? = null,
//		gender: String? = null,
//		change: String? = null,
//	) = repo.sportHallFilter(
//		startDate = startDate,
//		endDate = endDate,
//		typeOfSport = typeOfSport,
//		gender = gender,
//		change = change,
//	)
}