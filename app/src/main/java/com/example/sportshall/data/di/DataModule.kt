package com.example.sportshall.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.sportshall.data.local_source.SportHallDao
import com.example.sportshall.data.local_source.SportHallDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

	@Provides
	@Singleton
	fun provideDataBase(context: Application): SportHallDataBase {
		return Room.databaseBuilder(context, SportHallDataBase::class.java, "sportHall_db").build()
//		return SportHallDataBase.getDatabase(context)
	}

	@Provides
	@Singleton
	fun provideDao(dataBase: SportHallDataBase): SportHallDao {
		return dataBase.dao
	}
}