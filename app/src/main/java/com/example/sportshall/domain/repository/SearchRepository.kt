package com.example.sportshall.domain.repository

import androidx.paging.PagingData
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.ui.screens.search.model.SportHallInformation
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
	suspend fun searchSportHallInformation(name: String): Flow<List<SportHallInformation>>

}