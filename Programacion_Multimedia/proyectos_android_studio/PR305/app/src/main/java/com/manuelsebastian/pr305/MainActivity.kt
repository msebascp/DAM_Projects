package com.manuelsebastian.pr305

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import com.manuelsebastian.pr305.ui.theme.PR305Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR305Theme {
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
fun ViewContainer() {
	Scaffold(
		topBar = { Toolbar() },
		content = { paddingValues -> Content(modifier = Modifier.padding(paddingValues)) },
	)
}

@Composable
fun Content(modifier: Modifier) {
	var showIngresarNota by remember { mutableStateOf(false) }
	var nota by remember { mutableStateOf<Int?>(null) }

	Column(modifier = modifier) {
		Button(onClick = {
			showIngresarNota = true
		}) {
			Text("Ingresar nota")
		}
		if (showIngresarNota) {
			AskTextFieldValue(text = "Ingrese la nota", onTextFieldEntered = { number ->
				nota = number
				showIngresarNota = false
			})
		}
		when (nota) {
			null -> {
			}

			else -> {
				when (nota) {
					in 0..4 -> {
						Text("Suspenso")
					}

					in 5..7 -> {
						Text("Aprobado")
					}

					in 8..10 -> {
						Text("Excelente")
					}

					else -> {
						Text("Nota inválida")
					}
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskTextFieldValue(text: String, onTextFieldEntered: (Int) -> Unit) {
	var textFieldValue by remember { mutableStateOf("") }
	var showDialog by remember { mutableStateOf(true) }

	if (showDialog) {
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
						showDialog = false
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("PR305")
		},
		colors = TopAppBarDefaults.smallTopAppBarColors(
			containerColor = colorResource(id = R.color.purple_500),
			titleContentColor = colorResource(id = R.color.white),
		),
	)
}

@Preview(showBackground = true)
@Composable
fun ViewContainerPreview() {
	PR305Theme {
		ViewContainer()
	}
}