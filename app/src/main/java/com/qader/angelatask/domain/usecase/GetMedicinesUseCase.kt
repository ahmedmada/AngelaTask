package com.qader.angelatask.domain.usecase

import com.qader.angelatask.domain.model.MedicineModel
import com.qader.angelatask.domain.repositry.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(private val repository: MedicineRepository) {
    suspend operator fun invoke(): Flow<List<MedicineModel>> {
        return repository.fetchMedicines()
    }
}
