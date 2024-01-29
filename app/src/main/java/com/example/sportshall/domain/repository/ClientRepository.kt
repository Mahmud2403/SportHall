package com.example.sportshall.domain.repository

import com.example.sportshall.data.local_source.data.ClientEntity
import kotlinx.coroutines.flow.Flow


interface ClientRepository{
	suspend fun insertClients(clients: ClientEntity)
	suspend fun deleteClient(clients: ClientEntity)
	fun getListClients(): Flow<List<ClientEntity>>
}