package com.example.sportshall.data.local_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.SeasonTicketEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SportHallDao {
	@Upsert
	suspend fun upsertClients(clients: ClientEntity)

	@Delete
	suspend fun deleteClient(clients: ClientEntity)

	@Query("SELECT * FROM clientEntities")
	fun getListClients(): Flow<List<ClientEntity>>

	@Query("SELECT * FROM clientEntities WHERE birthDay BETWEEN :startDate and :endDate")
	fun filteringByBirthday(startDate: Long?, endDate: Long?): List<ClientEntity>

	@Query("SELECT * FROM trainer WHERE typeOfSport = :typeOfSport")
	fun filterByTypeOfSport(typeOfSport: String): List<TrainerEntity>

	@Query("SELECT * FROM clientEntities WHERE gender = :gender")
	fun filterByGender(gender: String): List<ClientEntity>

	@Query("SELECT * FROM seasonticket WHERE change = :change")
	fun filterByChange(change: String): List<SeasonTicketEntity>

	@Insert
	suspend fun insertSeasonTicket(seasonTicketEntity: SeasonTicketEntity)

	@Delete
	suspend fun deleteSeasonTicket(seasonTicketEntity: SeasonTicketEntity)

	@Query("SELECT * FROM seasonticket")
	fun getListSeasonTicket(): Flow<List<SeasonTicketEntity>>

	@Upsert
	suspend fun upsertTrainer(trainerEntity: TrainerEntity)

	@Delete
	suspend fun deleteTrainer(trainerEntity: TrainerEntity)

	@Query("SELECT * FROM trainer")
	fun getTrainers() : Flow<List<TrainerEntity>>

	@Query("SELECT * FROM trainer WHERE id=:id")
	fun getDetailInformationTrainer(id: Int): Flow<TrainerEntity>

	@Query("SELECT * FROM seasonticket WHERE id=:id")
	fun getDetailInformationSeasonTicket(id: Int): Flow<SeasonTicketEntity>


	@Query("SELECT * FROM clientEntities WHERE id=:id")
	fun getDetailInformationClient(id: Int) : Flow<ClientEntity>


	@Query("SELECT * FROM trainer WHERE firstName LIKE :searchQuery || '%' OR lastName LIKE :searchQuery || '%' OR patronymic LIKE :searchQuery || '%' ")
	fun searchTrainers(searchQuery: String): List<TrainerEntity>

	@Query("SELECT * FROM clientEntities WHERE firstName LIKE :searchQuery || '%' OR lastName LIKE :searchQuery || '%' OR patronymic LIKE :searchQuery || '%'")
	fun searchClient(searchQuery: String): List<ClientEntity>
}