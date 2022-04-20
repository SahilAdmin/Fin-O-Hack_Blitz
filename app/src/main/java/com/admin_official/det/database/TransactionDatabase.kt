package com.admin_official.det.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Transaction::class], version = 1)
abstract class TransactionDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: TransactionDatabase? = null

        fun getInstance(context: Context): TransactionDatabase {
            return instance?: synchronized(this) {
                instance?: Room.databaseBuilder(context,
                    TransactionDatabase::class.java,
                    "transactions")
                    .build().also {
                        instance = it
                    }
            }
        }
    }
}