package com.qader.angelatask.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qader.angelatask.utils.toJson

@Composable
fun MedicineListScreen(navController: NavController, viewModel: MainViewModel) {
    val medicines by viewModel.medicines.collectAsState()
    val greetingMessage by viewModel.greetingMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMedicines()
    }

    Column(
        modifier = Modifier
            .padding(top = 40.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = greetingMessage, style = MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(medicines) { medicine ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("medicineDetail/${medicine.toJson()}")
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Name: ${medicine.name}")
                        Text(text = "Dose: ${medicine.dose}")
                        Text(text = "Strength: ${medicine.strength}")
                    }
                }
            }
        }
    }
}
