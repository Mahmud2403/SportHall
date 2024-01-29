package com.example.sportshall.data.repository

import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.domain.repository.FilterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
	private val dao: SportHallDao
): FilterRepository {
	override suspend fun filteringByBirthday(startDate: Long?, endDate: Long?) =
		withContext(Dispatchers.IO) {
			dao.filteringByBirthday(startDate, endDate)
		}

	override suspend fun filterByTypeOfSport(typeOfSport: String) =
		withContext(Dispatchers.IO) {
			dao.filterByTypeOfSport(typeOfSport)
		}

	override suspend fun filterByGender(gender: String) =
		withContext(Dispatchers.IO) {
			dao.filterByGender(gender)
		}

	override suspend fun filterByChange(change: String) =
		withContext(Dispatchers.IO) {
			dao.filterByChange(change)
		}


//	override suspend fun sportHallFilter(
//		startDate: Long?,
//		endDate: Long?,
//		typeOfSport: String?,
//		gender: String?,
//		change: String?,
//	): Flow<List<SportHallInformation>> {
//		return withContext(Dispatchers.IO){
//			val gender = dao.filterByGender(gender).map(ClientEntity::toClient)
//			val birthDay = dao.filteringByBirthday(startDate = startDate, endDate = endDate).map(ClientEntity::toClient)
//			val typeOfSport = dao.filterByTypeOfSport(typeOfSport).map(TrainerEntity::toTrainer)
//
//			flowOf(
//				gender,
//				birthDay,
//				typeOfSport,
//			)
//		}
//	}
}