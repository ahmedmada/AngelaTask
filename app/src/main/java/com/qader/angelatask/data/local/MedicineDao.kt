package com.qader.angelatask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qader.angelatask.data.model.Medication

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): List<Medication>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(medications: List<Medication>)
}
