package com.example.sportshall.data.di

import com.example.sportshall.data.repository.ClientsRepositoryImpl
import com.example.sportshall.data.repository.DetailInformationRepositoryImpl
import com.example.sportshall.data.repository.FilterRepositoryImpl
import com.example.sportshall.data.repository.SearchRepositoryImpl
import com.example.sportshall.data.repository.SeasonTicketRepositoryImpl
import com.example.sportshall.data.repository.TrainerRepositoryImpl
import com.example.sportshall.domain.repository.ClientRepository
import com.example.sportshall.domain.repository.DetailInformationRepository
import com.example.sportshall.domain.repository.FilterRepository
import com.example.sportshall.domain.repository.SearchRepository
import com.example.sportshall.domain.repository.SeasonTicketRepository
import com.example.sportshall.domain.repository.TrainerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
abstract class RepoModule {
	@Binds
	abstract fun bindClientRepoImpl(repo: ClientsRepositoryImpl): ClientRepository

	@Binds
	abstract fun bindSeasonTicketRepoImpl(repo: SeasonTicketRepositoryImpl): SeasonTicketRepository

	@Binds
	abstract fun bindTrainerRepoImpl(repository: TrainerRepositoryImpl): TrainerRepository

	@Binds
	abstract fun bindDetailInformationRepoImpl(repository: DetailInformationRepositoryImpl): DetailInformationRepository

	@Binds
	abstract fun bindSearchRepoImpl(repository: SearchRepositoryImpl): SearchRepository

	@Binds
	abstract fun bindFilterRepoImpl(repository: FilterRepositoryImpl): FilterRepository
}