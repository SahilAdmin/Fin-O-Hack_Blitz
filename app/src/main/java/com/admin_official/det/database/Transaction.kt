package com.admin_official.det.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
class Transaction (@PrimaryKey(autoGenerate = true) val uid: Int, val description: String, val amount: Int)