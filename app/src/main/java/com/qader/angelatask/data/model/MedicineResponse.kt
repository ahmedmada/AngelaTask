package com.qader.angelatask.data.model

data class ProblemsResponse(
    val problems: List<Problem>
)

data class Problem(
    val Diabetes: List<DiabetesDetail>?,
)

data class DiabetesDetail(
    val medications: List<MedicationDetail>?,
    val labs: List<LabDetail>?
)

data class MedicationDetail(
    val medicationsClasses: List<MedicationsClass>?
)

data class MedicationsClass(
    val className: List<AssociatedDrugGroup>?,
    val className2: List<AssociatedDrugGroup>?
)

data class AssociatedDrugGroup(
    val associatedDrug: List<Medication>?,
    val associatedDrug2: List<Medication>?
)

data class LabDetail(
    val missing_field: String
)
