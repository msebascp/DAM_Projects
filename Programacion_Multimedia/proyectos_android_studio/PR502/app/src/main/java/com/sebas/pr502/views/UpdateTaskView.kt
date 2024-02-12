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
fun UpdateTaskView(
	navController: NavController,
	viewModel: TasksViewModel,
	id: Int,
	title: String,
	description: String
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("Actualizar tarea")
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
			ContentUpdateTask(
				modifier = Modifier.padding(paddingValues),
				viewModel,
				id,
				title,
				description,
				navController
			)
		},
	)
}

@Composable
fun ContentUpdateTask(
	modifier: Modifier,
	viewModel: TasksViewModel,
	id: Int,
	title: String,
	description: String,
	navController: NavController
) {
	var updateTitle by remember { mutableStateOf(title) }
	var updateDescription by remember { mutableStateOf(description) }
	var buttonEnable by remember { mutableStateOf(false) }
	Column(modifier = modifier.padding(16.dp)) {
		UpdateTitleField(updateTitle) {
			updateTitle = it
			buttonEnable = updateTitle.isNotEmpty() && updateDescription.isNotEmpty()
		}
		UpdateDescriptionField(updateDescription) {
			updateDescription = it
			buttonEnable = updateTitle.isNotEmpty() && updateDescription.isNotEmpty()
		}
		UpdateButton(buttonEnable = buttonEnable) {
			val updateTask = Task(
				updateTitle,
				updateDescription,
			)
			viewModel.updateTask(id, updateTask)
			navController.popBackStack()
		}
	}
}

@Composable
fun UpdateTitleField(title: String, onTextFieldChange: (String) -> Unit) {
	OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = title,
		onValueChange = { onTextFieldChange(it) },
		label = { Text(text = "Capital") },
		keyboardOptions = KeyboardOptions.Default.copy(
			imeAction = ImeAction.Next,
		),
		singleLine = true,
	)
}

@Composable
fun UpdateDescriptionField(description: String, onTextFieldChange: (String) -> Unit) {
	OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = description,
		onValueChange = { onTextFieldChange(it) },
		label = { Text(text = "Capital") },
		keyboardOptions = KeyboardOptions.Default.copy(
			imeAction = ImeAction.Done,
		),
		singleLine = true,
	)
}

@Composable
fun UpdateButton(buttonEnable: Boolean, onButtonClick: () -> Unit) {
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
			text = "Actualizar tarea"
		)
	}
}
