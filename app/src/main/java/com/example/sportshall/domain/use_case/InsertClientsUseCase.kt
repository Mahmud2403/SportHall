package com.example.sportshall.domain.use_case

import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.domain.repository.ClientRepository
import javax.inject.Inject

class InsertClientsUseCase @Inject constructor(
	private val repo: ClientRepository
){
	suspend operator fun invoke(clients: ClientEntity) = repo.insertClients(clients)
}