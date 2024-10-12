package com.qader.angelatask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qader.angelatask.domain.model.MedicineModel
import com.qader.angelatask.domain.usecase.GetMedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMedicinesUseCase: GetMedicinesUseCase
) : ViewModel() {

    private val _medicines = MutableStateFlow<List<MedicineModel>>(emptyList())
    val medicines: StateFlow<List<MedicineModel>> get() = _medicines

    private val _greetingMessage = MutableStateFlow("")
    val greetingMessage: StateFlow<String> get() = _greetingMessage

    fun login(username: String) {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val greeting = when {
            currentHour < 12 -> "Good Morning"
            currentHour < 18 -> "Good Afternoon"
            else -> "Good Evening"
        }
        _greetingMessage.value = "$greeting, $username!"
    }


    fun fetchMedicines() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getMedicinesUseCase.invoke().collect { medicinesList ->
                    _medicines.value = medicinesList
                }
            }
        }
    }
}
