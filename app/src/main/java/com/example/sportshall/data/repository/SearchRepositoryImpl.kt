package com.example.sportshall.data.repository

import android.util.Log
import androidx.paging.PagingData
import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.SearchData
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.data.local_source.data.toClient
import com.example.sportshall.data.local_source.data.toTrainer
import com.example.sportshall.domain.repository.SearchRepository
import com.example.sportshall.ui.screens.search.model.SportHallInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val dao: SportHallDao,
) : SearchRepository {

	companion object {
		const val TAG = "SearchRepositoryImpl"
	}
	override suspend fun searchSportHallInformation(name: String): Flow<List<SportHallInformation>> {
		return withContext(Dispatchers.IO) {
			val client = dao.searchClient(name).map(ClientEntity::toClient)
			val trainer = dao.searchTrainers(name).map(TrainerEntity::toTrainer)
			Log.d(
				TAG, "searchSportHallInformation:" +
						"name - $name, " +
						"client - $client, " +
						"trainer - $trainer"
			)
			 flowOf(client + trainer)
		}
	}
}