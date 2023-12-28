package com.manuelsebastian.pr304

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
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
import com.manuelsebastian.pr304.ui.theme.PR304Theme
import kotlin.random.Random
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR304Theme {
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
	var numeroAleatorio by remember { mutableStateOf(generarNumeroAleatorio()) }
	var numeroCorrecto by remember { mutableStateOf(false) }
	var showMensajeNumeroCorrecto by remember { mutableStateOf(false) }
	Column(modifier = modifier) {
		AskTextFieldValue(text = "Adivina el número", onTextFieldEntered = { number ->
			if (number == 0) {
				exitProcess(0)
			}
			numeroCorrecto = number == numeroAleatorio
			showMensajeNumeroCorrecto = true
			numeroAleatorio = generarNumeroAleatorio()
		})
		if (showMensajeNumeroCorrecto) {
			if (numeroCorrecto) {
				Text("¡Correcto!")
			} else {
				Text("¡Incorrecto!")
			}
		}
	}
}

fun generarNumeroAleatorio(): Int {
	// Genera un número aleatorio entre 1 (inclusive) y 11 (exclusive)
	return Random.nextInt(1, 11)
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
			Text("PR304")
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
	PR304Theme {
		ViewContainer()
	}
}