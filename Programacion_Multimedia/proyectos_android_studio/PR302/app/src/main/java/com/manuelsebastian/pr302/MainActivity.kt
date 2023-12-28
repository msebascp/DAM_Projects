package com.manuelsebastian.pr302

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
import com.manuelsebastian.pr302.ui.theme.PR302Theme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR302Theme {
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
	var saldo by remember { mutableStateOf(0.0) }
	var showSaldo by remember { mutableStateOf(false) }
	var showIngresarDinero by remember { mutableStateOf(false) }
	var showSacarDinero by remember { mutableStateOf(false) }
	var showMenu by remember { mutableStateOf(true) }
	if (showMenu) {
		Column(modifier = modifier) {
			Button(onClick = {
				showSaldo = true
			}) {
				Text("Ver saldo")
			}
			Button(onClick = {
				showIngresarDinero = true
			}) {
				Text("Ingresar dinero")
			}
			Button(onClick = {
				showSacarDinero = true
			}) {
				Text("Retirar dinero")
			}
			Button(onClick = {
				showMenu = false
			}) {
				Text("Salir")
			}
			if (showSaldo) {
				AlertDialogInfo(saldo = saldo, onCloseDialog = {
					showSaldo = false
				})
			}
			if (showIngresarDinero) {
				AskTextFieldValue(text = "Cantidad a ingresar", onTextFieldEntered = { number ->
					saldo += number
					showIngresarDinero = false
				})
			}
			if (showSacarDinero) {
				AskTextFieldValue(text = "Cantidad a retirar", onTextFieldEntered = { number ->
					saldo -= number
					showSacarDinero = false
				})
			}
		}
	} else {
		// Cierra la app
		exitProcess(0)
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

@Composable
fun AlertDialogInfo(saldo: Double, onCloseDialog: () -> Unit) {
	var showSaldo by remember { mutableStateOf(true) }
	if (showSaldo) {
		AlertDialog(
			onDismissRequest = { },
			confirmButton = {
				OutlinedButton(
					onClick = {
						showSaldo = false
						onCloseDialog()
					}
				) {
					Text("Aceptar")
				}
			},
			text = {
				Text("Saldo: $saldo")
			}
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("PR302")
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
	PR302Theme {
		ViewContainer()
	}
}