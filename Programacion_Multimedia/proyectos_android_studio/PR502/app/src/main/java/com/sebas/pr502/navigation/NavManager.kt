package com.sebas.pr502.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sebas.pr502.viewModels.TasksViewModel
import com.sebas.pr502.views.AllTasksView
import com.sebas.pr502.views.UpdateTaskView
import com.sebas.pr502.views.NewTaskView

@Composable
fun NavManager(viewModel: TasksViewModel) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = "allTasks") {
		composable("allTasks") {
			AllTasksView(navController, viewModel)
		}
		composable("newTask") {
			NewTaskView(navController, viewModel)
		}
		composable("updateTask/{id}/{title}/{description}", arguments = listOf(
			navArgument("id") { type = NavType.IntType },
			navArgument("title") { type = NavType.StringType },
			navArgument("description") { type = NavType.StringType }
		)
		) {
			UpdateTaskView(
				navController,
				viewModel,
				it.arguments!!.getInt("id"),
				it.arguments!!.getString("title")!!,
				it.arguments!!.getString("description")!!
			)
		}
	}
}