package com.sebas.pr502.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sebas.pr502.models.Task
import com.sebas.pr502.viewModels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTasksView(navController: NavController, viewModel: TasksViewModel) {
	var isMenuExpanded by remember { mutableStateOf(false) }
	var showAlertDelete by remember { mutableStateOf(false) }
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("Todas las tareas")
				},
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primary,
					titleContentColor = MaterialTheme.colorScheme.onPrimary,
				),
				actions = {
					IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
						Icon(
							imageVector = Icons.Default.Menu,
							contentDescription = "Menú desplegable",
							tint = MaterialTheme.colorScheme.onPrimary
						)
					}
					// Dropdown menu
					DropdownMenu(
						expanded = isMenuExpanded,
						onDismissRequest = { isMenuExpanded = false }
					) {
						DropdownMenuItem(
							text = { Text("Añadir tarea") },
							onClick = {
								isMenuExpanded = false
								navController.navigate("newTask")
							}
						)
						DropdownMenuItem(
							text = { Text("Eliminar todas las tareas") },
							onClick = {
								isMenuExpanded = false
								showAlertDelete = true
							}
						)
					}
				}
			)
		},
		content = { paddingValues ->
			ContentAllTasks(
				modifier = Modifier.padding(paddingValues),
				viewModel,
				navController
			)
		},
		floatingActionButton = {
			FloatingActionButton(onClick = {
				navController.navigate("newTask")
			}) {
				Icon(
					imageVector = Icons.Default.Add,
					contentDescription = "Añadir tarea"
				)
			}
		}
	)
	if (showAlertDelete) {
		DeleteTasks(viewModel) {
			showAlertDelete = false
		}
	}
}

@Composable
fun ContentAllTasks(modifier: Modifier, viewModel: TasksViewModel, nav: NavController) {
	val tasks: MutableList<Task> by viewModel.tasks.observeAsState(mutableListOf())
	LazyColumn(modifier = modifier.padding(16.dp)) {
		itemsIndexed(tasks) { index, task ->
			CardTask(index, task, nav)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTask(index: Int, task: Task, nav: NavController) {
	ElevatedCard(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 16.dp),
		elevation = CardDefaults.cardElevation(
			defaultElevation = 6.dp
		),
		onClick = {
			nav.navigate("updateTask/${index}/${task.title}/${task.description}")
		}
	) {
		Column(modifier = Modifier.padding(10.dp)) {
			Text(
				style = MaterialTheme.typography.titleMedium,
				text = task.title
			)
			Text(
				style = MaterialTheme.typography.bodyMedium,
				text = task.description
			)
		}
	}
}

@Composable
fun DeleteTasks(viewModel: TasksViewModel, onFinishAlert: () -> Unit) {
	var showDialog by remember { mutableStateOf(true) }

	if (showDialog) {
		AlertDialog(
			onDismissRequest = {
				showDialog = false
				onFinishAlert()
			},
			title = { Text("Eliminar todas las tareas") },
			text = { Text("¿Estás seguro de que deseas eliminar todas las tareas?") },
			confirmButton = {
				Button(
					onClick = {
						viewModel.deleteAllTasks()
						showDialog = false
						onFinishAlert()
					}
				) {
					Text("Sí")
				}
			},
			dismissButton = {
				Button(
					onClick = {
						showDialog = false
						onFinishAlert()
					}
				) {
					Text("No")
				}
			}
		)
	}
}


