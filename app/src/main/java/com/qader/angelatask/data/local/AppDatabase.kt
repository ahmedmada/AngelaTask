package com.qader.angelatask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qader.angelatask.data.model.Medication

@Database(entities = [Medication::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}

