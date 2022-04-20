package com.admin_official.det.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert
    fun addTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    fun query(): LiveData<List<Transaction>>
}