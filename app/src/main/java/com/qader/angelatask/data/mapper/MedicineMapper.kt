package com.qader.angelatask.data.mapper

import com.qader.angelatask.data.model.Medication
import com.qader.angelatask.data.model.ProblemsResponse
import com.qader.angelatask.domain.model.MedicineModel

class MedicineMapper {
    fun mapResponseToDatabase(response: ProblemsResponse): List<Medication> {
        return response.problems.flatMap { problem ->
            problem.Diabetes?.flatMap { diabetesDetail ->
                diabetesDetail.medications?.flatMap { medicationDetail ->
                    medicationDetail.medicationsClasses?.flatMap { classItem ->
                        val drugsFromClassName =
                            classItem.className?.flatMap { associatedDrugGroup ->
                                associatedDrugGroup.associatedDrug ?: emptyList()
                            } ?: emptyList()

                        val drugsFromClassName2 =
                            classItem.className2?.flatMap { associatedDrugGroup2 ->
                                associatedDrugGroup2.associatedDrug2 ?: emptyList()
                            } ?: emptyList()

                        drugsFromClassName + drugsFromClassName2
                    } ?: emptyList()
                } ?: emptyList()
            } ?: emptyList()
        }
    }

    fun mapResponseToDomain(medications: List<Medication>): List<MedicineModel> {
        return medications.map { medicine ->
            MedicineModel(
                id = medicine.id,
                name = medicine.name,
                dose = medicine.dose,
                strength = medicine.strength
            )
        }
    }
}
