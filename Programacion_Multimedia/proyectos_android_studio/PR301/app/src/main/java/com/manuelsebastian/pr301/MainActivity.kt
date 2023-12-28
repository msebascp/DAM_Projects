package com.manuelsebastian.pr301

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
import com.manuelsebastian.pr301.ui.theme.PR301Theme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR301Theme {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("PR301")
		},
		colors = TopAppBarDefaults.smallTopAppBarColors(
			containerColor = colorResource(id = R.color.purple_500),
			titleContentColor = colorResource(id = R.color.white),
		),
	)
}

@Composable
fun Content(modifier: Modifier) {
	var liters by remember { mutableStateOf(0) }
	var showAskLiters by remember { mutableStateOf(false) }
	var glassDiameter by remember { mutableStateOf(0) }
	var showAskGlassDiameter by remember { mutableStateOf(false) }
	var glassHeight by remember { mutableStateOf(0) }
	var showAskGlassHeight by remember { mutableStateOf(false) }
	var drunkGlasses by remember { mutableStateOf(0) }
	var showAskDrunkGlasses by remember { mutableStateOf(false) }

	Column(modifier = modifier) {
		Button(onClick = {
			showAskLiters = true
		}) {
			Text("Litros para estar perjudicado")
		}
		Button(onClick = {
			showAskGlassDiameter = true
		}) {
			Text("Ingresar diámetro del vaso(cm)")
		}
		Button(onClick = {
			showAskGlassHeight = true
		}) {
			Text("Ingresar altura del vaso(cm)")
		}
		Button(onClick = {
			showAskDrunkGlasses = true
		}) {
			Text("Ingresar vasos que has tomado")
		}
		if (showAskLiters) {
			AskTextFieldValue(text = "Litros", onTextFieldEntered = { number ->
				liters = number
				showAskLiters = false
			})
		}
		if (showAskGlassDiameter) {
			AskTextFieldValue(text = "Diámetro del vaso", onTextFieldEntered = { number ->
				glassDiameter = number
				showAskGlassDiameter = false
			})
		}
		if (showAskGlassHeight) {
			AskTextFieldValue(text = "Altura del vaso", onTextFieldEntered = { number ->
				glassHeight = number
				showAskGlassHeight = false
			})
		}
		if (showAskDrunkGlasses) {
			AskTextFieldValue(text = "Vasos que has tomado", onTextFieldEntered = { number ->
				drunkGlasses = number
				showAskDrunkGlasses = false
			})
		}
		Text(text = "Litros: $liters")
		Text(text = "Diámetro del vaso: $glassDiameter")
		Text(text = "Altura del vaso: $glassHeight")
		Text(text = "Vasos que has tomado: $drunkGlasses")
		if (liters > 0 && glassDiameter > 0 && glassHeight > 0 && drunkGlasses > 0) {
			val volumeOneGlass = calculateVolume(glassDiameter, glassHeight)
			val volumeDrunked = volumeOneGlass * drunkGlasses
			Text(text = "Has tomado $volumeDrunked litros")
			if (volumeDrunked > liters) {
				Text(text = "Estás perjudicado")
			} else {
				Text(text = "No estás perjudicado")
			}
		}
	}
}

fun calculateVolume(glassDiameter: Int, glassHeight: Int): Double {
	val radius = glassDiameter.toDouble() / 2
	val glassHeightDouble = glassHeight.toDouble()
	var volume = (Math.PI * (radius) * (radius) * glassHeightDouble) / 1000
	volume = (volume * 10000).roundToInt() / 10000.0
	return volume
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewContainerPreview() {
	PR301Theme {
		ViewContainer()
	}
}