package com.example.budgetkit.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.budgetkit.models.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(postResponse: List<Transaction>)

    @Query("select * from transactions")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("select * from transactions where category:=category")
    fun getAllTransactionsOfCategory(category:String): Flow<List<Transaction>>

    @Query("select * from transactions where id=:transactionId")
    fun getTransaction(transactionId: String): Flow<Transaction>

}