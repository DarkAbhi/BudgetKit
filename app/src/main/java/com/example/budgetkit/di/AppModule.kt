package com.example.budgetkit.di

import android.content.Context
import com.example.budgetkit.data.repository.TransactionRepositoryImpl
import com.example.budgetkit.data.room.dao.TransactionDao
import com.example.budgetkit.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext appContext: Context) =
        PreferencesManager(appContext)

    @Provides
    @Singleton
    fun providesTransactionRepository(transactionDao: TransactionDao) =
        TransactionRepositoryImpl(transactionDao = transactionDao)

}