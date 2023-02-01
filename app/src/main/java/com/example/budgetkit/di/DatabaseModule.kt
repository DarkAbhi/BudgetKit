package com.example.budgetkit.di

import android.app.Application
import androidx.room.Room
import com.example.budgetkit.data.room.BudgetKitDatabase
import com.example.budgetkit.data.room.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BudgetKitDatabase {
        return Room.databaseBuilder(
            application,
            BudgetKitDatabase::class.java,
            "budget_kit_app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTransactionDao(budgetKitDatabase: BudgetKitDatabase): TransactionDao {
        return budgetKitDatabase.transactionDao()
    }

}