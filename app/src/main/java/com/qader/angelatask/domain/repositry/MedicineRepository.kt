package com.qader.angelatask.domain.repositry

import com.qader.angelatask.domain.model.MedicineModel
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun fetchMedicines(): Flow<List<MedicineModel>>
}
