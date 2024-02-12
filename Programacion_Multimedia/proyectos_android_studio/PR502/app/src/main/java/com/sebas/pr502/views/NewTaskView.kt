package com.sebas.pr502.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sebas.pr502.models.Task
import com.sebas.pr502.viewModels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskView(navController: NavController, viewModel: TasksViewModel) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("Añaadir tarea")
				},
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primary,
					titleContentColor = MaterialTheme.colorScheme.onPrimary,
				),
				navigationIcon = {
					IconButton(
						onClick = { navController.popBackStack() }
					) {
						Icon(
							imageVector = Icons.Default.ArrowBack,
							contentDescription = "Volver a la vista anterior",
						)
					}
				},
			)
		},
		content = { paddingValues ->
			ContentNewTask(
				modifier = Modifier.padding(paddingValues),
				viewModel,
				navController
			)
		},
	)
}

@Composable
fun ContentNewTask(modifier: Modifier, viewModel: TasksViewModel, navController: NavController) {
	var title by remember { mutableStateOf("") }
	var description by remember { mutableStateOf("") }
	var buttonEnable by remember { mutableStateOf(false) }
	Column(modifier = modifier.padding(16.dp)) {
		NewTitleField(title, onTextFieldChange = {
			title = it
			buttonEnable = title.isNotEmpty() && description.isNotEmpty()
		})
		NewDescriptionField(description, onTextFieldChange = {
			description = it
			buttonEnable = title.isNotEmpty() && description.isNotEmpty()
		})
		AddButton(buttonEnable = buttonEnable, onButtonClick = {
			val newTask = Task(title = title, description = description)
			viewModel.addTask(newTask)
			navController.popBackStack()
		})
	}
}

@Composable
fun NewTitleField(title: String, onTextFieldChange: (String) -> Unit) {
	OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = title,
		onValueChange = {
			onTextFieldChange(it)
		},
		label = { Text(text = "Título") },
		keyboardOptions = KeyboardOptions.Default.copy(
			imeAction = ImeAction.Next,
		),
		singleLine = true,
	)
}

@Composable
fun NewDescriptionField(description: String, onTextFieldChange: (String) -> Unit) {
	OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = description,
		onValueChange = {
			onTextFieldChange(it)
		},
		label = { Text(text = "Descripción") },
		keyboardOptions = KeyboardOptions.Default.copy(
			imeAction = ImeAction.Done,
		),
		singleLine = true,
	)
}

@Composable
fun AddButton(buttonEnable: Boolean, onButtonClick: () -> Unit) {
	FilledTonalButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 10.dp),
		onClick = { onButtonClick() },
		enabled = buttonEnable,
		contentPadding = PaddingValues(vertical = 16.dp)
	) {
		Text(
			style = MaterialTheme.typography.titleLarge,
			text = "Añadir tarea"
		)
	}
}
