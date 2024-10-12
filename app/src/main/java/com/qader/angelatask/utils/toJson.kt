package com.qader.angelatask.utils

import com.google.gson.Gson
import com.qader.angelatask.domain.model.MedicineModel

fun MedicineModel.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}
