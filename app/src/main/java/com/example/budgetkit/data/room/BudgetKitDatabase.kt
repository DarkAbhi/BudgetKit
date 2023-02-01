package com.example.budgetkit.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budgetkit.data.room.dao.TransactionDao
import com.example.budgetkit.models.Transaction

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class BudgetKitDatabase:RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

}