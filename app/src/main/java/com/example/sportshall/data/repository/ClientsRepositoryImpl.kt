package com.example.sportshall.data.repository

import android.util.Log
import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.domain.repository.ClientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientsRepositoryImpl @Inject constructor(
	private val dao: SportHallDao,
): ClientRepository {
	override suspend fun insertClients(clients: ClientEntity) {
		withContext(Dispatchers.IO) {
			dao.upsertClients(clients)
			Log.i("insertClients", "$clients")
		}
	}
	override fun getListClients() = dao.getListClients()
	override suspend fun deleteClient(clients: ClientEntity) {
		withContext(Dispatchers.IO) {
			dao.deleteClient(clients)
		}
	}
}