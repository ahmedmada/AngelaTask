package com.qader.angelatask.domain.usecase

import com.qader.angelatask.domain.model.MedicineModel
import org.junit.Assert.*
import com.qader.angelatask.domain.repositry.MedicineRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class GetMedicinesUseCaseTest {

    private lateinit var getMedicinesUseCase: GetMedicinesUseCase
    private lateinit var repository: MedicineRepository

    @Before
    fun setUp() {
        // Create a mock instance of the repository
        repository = mock()
        // Initialize the use case with the mock repository
        getMedicinesUseCase = GetMedicinesUseCase(repository)
    }

    @Test
    fun `invoke should return list of medicines successfully`() = runTest {
        // Arrange
        val mockMedicines = listOf(
            MedicineModel(id = 1, name = "Medicine 1", dose = "500mg",strength=""),
            MedicineModel(id = 2, name = "Medicine 2", dose = "250mg",strength="")
        )
        // Mock the repository to return a flow of the medicine list
        `when`(repository.fetchMedicines()).thenReturn(flow { emit(mockMedicines) })

        // Act
        val result = getMedicinesUseCase.invoke()

        // Assert
        result.collect { medicines ->
            assertEquals(2, medicines.size)
            assertEquals("Medicine 1", medicines[0].name)
            assertEquals("Medicine 2", medicines[1].name)
        }

        // Verify that the repository was called once
        verify(repository, times(1)).fetchMedicines()
    }

    @Test
    fun `invoke should return an empty list of medicines`() = runTest {
        // Arrange
        val emptyList = listOf<MedicineModel>()
        // Mock the repository to return a flow of an empty list
        `when`(repository.fetchMedicines()).thenReturn(flow { emit(emptyList) })

        // Act
        val result = getMedicinesUseCase.invoke()

        // Assert
        result.collect { medicines ->
            assertTrue(medicines.isEmpty())
        }

        // Verify that the repository was called once
        verify(repository, times(1)).fetchMedicines()
    }

    @Test
    fun `invoke should throw an exception when repository fails`() = runTest {
        // Arrange
        val exception = RuntimeException("Failed to fetch medicines")
        // Mock the repository to throw an exception
        `when`(repository.fetchMedicines()).thenThrow(exception)

        // Act & Assert
        try {
            getMedicinesUseCase.invoke().collect { medicines ->
                // Check the size or contents of the emitted list
                assertEquals(2, medicines.size)
                assertEquals("Medicine 1", medicines[0].name)
            }
            fail("Exception was expected but not thrown")
        } catch (e: Exception) {
            assertEquals("Failed to fetch medicines", e.message)
        }

        // Verify that the repository was called once
        verify(repository, times(1)).fetchMedicines()
    }
}
