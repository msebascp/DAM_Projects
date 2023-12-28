package com.manuelsebastian.pr401

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelsebastian.pr401.ui.theme.PR401Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR401Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					ViewContainer()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewContainer(modifier: Modifier = Modifier) {
	Scaffold(
		topBar = { Toolbar() },
		content = { paddingValues -> Content(modifier = Modifier.padding(paddingValues)) },
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(modifier: Modifier) {
	var profesor by remember { mutableStateOf(Profesor(0)) }
	var notasList by remember { mutableStateOf(profesor.getNotas()) }
	var showDialog by remember { mutableStateOf(false) }
	var showAskNotesNumber by remember { mutableStateOf(false) }
	var showAskIndexToDelete by remember { mutableStateOf(false) }
	var showAskNewNotes by remember { mutableStateOf(false) }
	var textDialog by remember { mutableStateOf("") }
	var index by remember { mutableStateOf(0) }
	var textFieldValue by remember { mutableStateOf("") }

	Column(
		modifier = modifier
			.padding(16.dp)
			.verticalScroll(rememberScrollState())
	) {
		Button(
			onClick = { showAskNotesNumber = true },
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Establecer número de notas")
		}
		Button(
			onClick = {
				if (notasList.isNotEmpty()) {
					showAskNewNotes = true
				} else {
					textDialog = "No hay notas."
					showDialog = true
				}
			},
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Poner notas")
		}
		if (showAskNewNotes && index < notasList.size) {
			OutlinedTextField(
				value = textFieldValue,
				onValueChange = { it ->
					val filteredText = it.filter { it.isDigit() }
					textFieldValue = filteredText
				},
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Number
				),
				label = { Text("Nota[$index]:") }
			)
			Button(onClick = {
				if (textFieldValue.isNotBlank()) {
					val number = textFieldValue.toInt()
					profesor.setNota(index, number)
					textFieldValue = ""
					index++
				}
				if (index == notasList.size) {
					showAskNewNotes = false
					index = 0
				}
			}) {
				Text(text = "Aceptar")
			}
		}
		Button(
			onClick = {
				textDialog = profesor.getMaxNota()
				showDialog = true
			},
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Obtener nota máxima")
		}
		Button(
			onClick = {
				textDialog = profesor.calculateMedia()
				showDialog = true
			},
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Calcular media truncada")
		}
		Button(
			onClick = { showAskIndexToDelete = true },
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Eliminar nota")
		}
		Button(
			onClick = {
				profesor.deleteAllNotas()
				notasList = profesor.getNotas()
			},
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Eliminar todas las notas")
		}
		if (notasList.isNotEmpty()) {
			Text(text = "Notas:")
			for (i in notasList.indices) {
				Text(text = "Nota[$i]: ${notasList[i]}")
			}
		}
		if (showDialog) {
			AlertDialogInfo(textDialog, showDialog) {
				showDialog = false
			}
		}
		if (showAskNotesNumber) {
			AskNumber("Número de notas a ingresar", true) {
				profesor = Profesor(it)
				notasList = profesor.getNotas()
				showAskNotesNumber = false
			}
		}
		if (showAskIndexToDelete) {
			AskNumber("Índice de la nota a eliminar", true) {
				when (it) {
					in 0 until notasList.size -> {
						profesor.deleteNota(it)
						notasList = profesor.getNotas()
					}

					else -> {
						textDialog = "El índice no existe."
						showDialog = true
					}
				}
				showAskIndexToDelete = false
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskNumber(text: String, showDialog: Boolean, onTextFieldEntered: (Int) -> Unit) {
	var textFieldValue by remember { mutableStateOf("") }
	var show by remember { mutableStateOf(showDialog) }

	if (show) {
		AlertDialog(
			onDismissRequest = {},
			confirmButton = {
				OutlinedButton(
					onClick = {
						if (textFieldValue.isNotBlank()) {
							val number = textFieldValue.toInt()
							textFieldValue = ""
							onTextFieldEntered(number)
						} else {
							onTextFieldEntered(0)
						}
						show = false
					}
				) {
					Text("Aceptar")
				}
			},
			text = {
				OutlinedTextField(
					value = textFieldValue,
					onValueChange = { it ->
						val filteredText = it.filter { it.isDigit() }
						textFieldValue = filteredText
					},
					keyboardOptions = KeyboardOptions.Default.copy(
						keyboardType = KeyboardType.Number
					),
					label = { Text(text) }
				)
			}
		)
	}
}

@Composable
fun AlertDialogInfo(textInfo: String, showDialog: Boolean, onCloseDialog: () -> Unit = {}) {
	var show by remember { mutableStateOf(showDialog) }
	if (show) {
		AlertDialog(
			onDismissRequest = { },
			confirmButton = {
				OutlinedButton(
					onClick = {
						show = false
						onCloseDialog()
					}
				) {
					Text("Aceptar")
				}
			},
			text = {
				Text(text = textInfo)
			}
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("PR401")
		},
		colors = TopAppBarDefaults.smallTopAppBarColors(
			containerColor = colorResource(id = R.color.purple_500),
			titleContentColor = colorResource(id = R.color.white),
		),
	)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewContainerPreview() {
	PR401Theme {
		ViewContainer()
	}
}