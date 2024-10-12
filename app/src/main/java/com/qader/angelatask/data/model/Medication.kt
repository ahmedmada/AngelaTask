package com.qader.angelatask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medicines")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dose: String,
    val strength: String
)

