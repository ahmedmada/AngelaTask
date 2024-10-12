package com.qader.angelatask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicineModel(
    val id: Int,
    val name: String,
    val dose: String,
    val strength: String
) : Parcelable
