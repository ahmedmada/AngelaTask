package com.qader.angelatask.data.repositry

import android.util.Log
import com.qader.angelatask.data.local.MedicineDao
import com.qader.angelatask.data.mapper.MedicineMapper
import com.qader.angelatask.data.remote.ApiService
import com.qader.angelatask.domain.model.MedicineModel
import com.qader.angelatask.domain.repositry.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MedicineRepositoryImpl(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao,
    private val mapper: MedicineMapper
) : MedicineRepository {

    override suspend fun fetchMedicines(): Flow<List<MedicineModel>> = flow {
        val response = apiService.getMedicines()
        val medicines = mapper.mapResponseToDatabase(response)
        medicineDao.insertAll(medicines)
        emit(mapper.mapResponseToDomain(medicineDao.getAllMedicines()))
    }
}
