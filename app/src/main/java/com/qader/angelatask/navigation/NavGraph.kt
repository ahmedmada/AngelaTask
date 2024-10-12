package com.qader.angelatask.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.qader.angelatask.domain.model.MedicineModel
import com.qader.angelatask.presentation.LoginScreen
import com.qader.angelatask.presentation.MainViewModel
import com.qader.angelatask.presentation.MedicineDetailScreen
import com.qader.angelatask.presentation.MedicineListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, viewModel) }
        composable("medicines") { MedicineListScreen(navController, viewModel) }
        composable(
            "medicineDetail/{medicine}",
            arguments = listOf(navArgument("medicine") { type = NavType.StringType })
        ) { backStackEntry ->
            val medicineJson = backStackEntry.arguments?.getString("medicine")
            val medicine = Gson().fromJson(medicineJson, MedicineModel::class.java)
            medicine?.let { MedicineDetailScreen(navController, it) }
        }

    }
}
