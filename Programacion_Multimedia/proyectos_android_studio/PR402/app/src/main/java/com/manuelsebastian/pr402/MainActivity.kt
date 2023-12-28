package com.manuelsebastian.pr402

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuelsebastian.pr402.ui.theme.PR402Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR402Theme {
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

enum class VehiculoType {
	COCHE, MOTO, PATINETE, FURGONETA, TRAILER
}

enum class OptionsMenu {
	CREAR, CONSULTARCANTIDAD
}

@Composable
fun Content(modifier: Modifier) {
	// Controla si se muestra el dialogo para preguntar por el tipo de vehículo
	var showAskVehiculoType by remember { mutableStateOf(false) }
	// Controla si se muestra el toast
	var showToast by remember { mutableStateOf(false) }
	// Controla si se muestra el alert dialog que muestra información
	var showAlertInfo by remember { mutableStateOf(false) }
	// Controla si se muestra el formulario para crear un vehículo, y ocultar el resto de elementos
	var showForm by remember { mutableStateOf(false) }
	// Controla si se muestra el array de vehículos filtrado, o el array de vehículos completo
	var showFilteredArrayVehiculos by remember { mutableStateOf(false) }
	var textToast by remember { mutableStateOf("") }
	var textInfo by remember { mutableStateOf("") }
	var optionMenu by remember { mutableStateOf(OptionsMenu.CREAR) }
	var vehiculoType by remember { mutableStateOf(VehiculoType.COCHE) }
	var arrayVehiculos by remember { mutableStateOf(emptyArray<Vehiculo?>()) }
	var filteredArrayVehiculos by remember { mutableStateOf(emptyArray<Vehiculo?>()) }
	if (showForm) {
		LazyColumn(
			modifier = modifier
				.padding(16.dp)
		) {
			item {
				FilledTonalButton(onClick = { showForm = false }) {
					Icon(
						Icons.Rounded.ArrowBack,
						contentDescription = "Go back"
					)
				}
				Text(
					modifier = Modifier
						.fillMaxWidth(),
					text = "Crear nuevo vehículo",
					textAlign = TextAlign.Center
				)
				FormVehiculo(vehiculoType, onFormCompleted = { vehiculo ->
					var validate = false
					if (vehiculo is Moto) {
						if (vehiculo.numAsientos > 2) {
							textToast = "Las motos no pueden tener más de 2 asientos"
							showToast = true
						} else {
							validate = true
						}
					} else if (vehiculo is Furgoneta) {
						if (vehiculo.numRuedas > 6) {
							textToast = "Las furgonetas no pueden tener más de 6 ruedas"
							showToast = true
						} else {
							validate = true
						}
					} else if (vehiculo is Trailer) {
						if (vehiculo.numRuedas < 6) {
							textToast = "Los trailers no pueden tener menos de 6 ruedas"
							showToast = true
						} else {
							validate = true
						}
					} else {
						validate = true
					}
					if (validate) {
						val listaAux = arrayVehiculos.copyOf()
						arrayVehiculos = Array<Vehiculo?>(arrayVehiculos.size + 1) { null }
						for (i in listaAux.indices) {
							arrayVehiculos[i] = listaAux[i]
						}
						arrayVehiculos[arrayVehiculos.size - 1] = vehiculo
						showForm = false
					}
				})
			}
		}
	} else {
		Column(
			verticalArrangement = Arrangement.spacedBy(16.dp),
			modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
		) {
			FilledTonalButton(
				modifier = Modifier
					.fillMaxWidth(),
				onClick = {
					optionMenu = OptionsMenu.CREAR
					showAskVehiculoType = true
				},
				contentPadding = PaddingValues(vertical = 16.dp)
			) {
				Text(
					fontSize = 16.sp,
					text = "Crear nuevo vehículo"
				)
			}
			FilledTonalButton(
				modifier = Modifier.fillMaxWidth(),
				onClick = {
					optionMenu = OptionsMenu.CONSULTARCANTIDAD
					showAskVehiculoType = true
				},
				contentPadding = PaddingValues(vertical = 16.dp)
			) {
				Text(
					fontSize = 16.sp,
					text = "Consultar cantidad de un tipo de vehículo"
				)
			}
			SearchVehiculo(arrayVehiculos) { filteredArray, showFilteredArray ->
				filteredArrayVehiculos = filteredArray
				showFilteredArrayVehiculos = showFilteredArray
			}
			if (showFilteredArrayVehiculos) {
				LazyColumn(contentPadding = PaddingValues(top = 3.dp)) {
					items(filteredArrayVehiculos.size) { index ->
						CardVehiculo(vehiculo = filteredArrayVehiculos[index])
					}
				}
			} else {
				LazyColumn(contentPadding = PaddingValues(top = 3.dp)) {
					items(arrayVehiculos.size) { index ->
						CardVehiculo(vehiculo = arrayVehiculos[index])
					}
				}
			}
		}
	}
	if (showAskVehiculoType) {
		AskVehiculoType(true) {
			showAskVehiculoType = false
			when (it) {
				1 -> {
					if (optionMenu == OptionsMenu.CONSULTARCANTIDAD) {
						var cantidad = 0
						for (vehiculo in arrayVehiculos) {
							if (vehiculo is Coche) {
								cantidad++
							}
						}
						textInfo = "Hay $cantidad coches"
						showAlertInfo = true
					} else if (optionMenu == OptionsMenu.CREAR) {
						vehiculoType = VehiculoType.COCHE
						showForm = true
					}
				}

				2 -> {
					if (optionMenu == OptionsMenu.CONSULTARCANTIDAD) {
						var cantidad = 0
						for (vehiculo in arrayVehiculos) {
							if (vehiculo is Moto) {
								cantidad++
							}
						}
						textInfo = "Hay $cantidad motos"
						showAlertInfo = true
					} else if (optionMenu == OptionsMenu.CREAR) {
						vehiculoType = VehiculoType.MOTO
						showForm = true
					}
				}

				3 -> {
					if (optionMenu == OptionsMenu.CONSULTARCANTIDAD) {
						var cantidad = 0
						for (vehiculo in arrayVehiculos) {
							if (vehiculo is Patinete) {
								cantidad++
							}
						}
						textInfo = "Hay $cantidad patinetes"
						showAlertInfo = true
					} else if (optionMenu == OptionsMenu.CREAR) {
						vehiculoType = VehiculoType.PATINETE
						showForm = true
					}
				}

				4 -> {
					if (optionMenu == OptionsMenu.CONSULTARCANTIDAD) {
						var cantidad = 0
						for (vehiculo in arrayVehiculos) {
							if (vehiculo is Furgoneta) {
								cantidad++
							}
						}
						textInfo = "Hay $cantidad furgonetas"
						showAlertInfo = true
					} else if (optionMenu == OptionsMenu.CREAR) {
						vehiculoType = VehiculoType.FURGONETA
						showForm = true
					}
				}

				5 -> {
					if (optionMenu == OptionsMenu.CONSULTARCANTIDAD) {
						var cantidad = 0
						for (vehiculo in arrayVehiculos) {
							if (vehiculo is Trailer) {
								cantidad++
							}
						}
						textInfo = "Hay $cantidad trailers"
						showAlertInfo = true
					} else if (optionMenu == OptionsMenu.CREAR) {
						vehiculoType = VehiculoType.TRAILER
						showForm = true
					}
				}

				else -> {
					textToast = "Opción no válida"
					showToast = true
				}
			}
		}
	}
	if (showToast) {
		Toast.makeText(LocalContext.current, textToast, Toast.LENGTH_SHORT)
			.show()
		showToast = false
	}
	if (showAlertInfo) {
		AlertDialogInfo(textInfo = textInfo, true) {
			showAlertInfo = false
		}
	}
}

/**
 * AlertDialog que muestra información
 */
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

/**
 * TextField para buscar un vehículo por modelo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchVehiculo(
	arrayVehiculos: Array<Vehiculo?>,
	onInputChange: (Array<Vehiculo?>, showFilteredArray: Boolean) -> Unit
) {
	var text by remember { mutableStateOf("") }
	OutlinedTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(0.dp),
		value = text,
		onValueChange = { inputText ->
			text = inputText
			if (inputText == "") {
				onInputChange(emptyArray<Vehiculo?>(), false)
			} else {
				val filteredArray = arrayVehiculos.filter {
					it?.modelo?.contains(text, ignoreCase = true) == true
				}.toTypedArray()
				onInputChange(filteredArray, true)
			}
		},
		label = { Text("Buscar por modelo") }
	)
}

/**
 * Card que muestra la información de un vehículo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardVehiculo(vehiculo: Vehiculo?) {
	var showAll by remember { mutableStateOf(false) }
	ElevatedCard(
		elevation = CardDefaults.cardElevation(
			defaultElevation = 6.dp
		),
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 20.dp),
		// Cuando se pulsa sobre la card se expande o contrae
		onClick = {
			showAll = !showAll
		}
	) {
		Row(
			modifier = Modifier
				.fillMaxHeight()
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			Column(
				modifier = Modifier
					.fillMaxHeight()
					.padding(16.dp),
			) {
				Text(text = "Tipo: ${vehiculo?.javaClass?.simpleName}")
				Text(text = "Modelo: ${vehiculo?.modelo}")
				Text(text = "Color: ${vehiculo?.color}")
				if (showAll) {
					Text(text = "Número de ruedas: ${vehiculo?.numRuedas}")
					Text(text = "Motor: ${vehiculo?.motor}")
					Text(text = "Número de asientos: ${vehiculo?.numAsientos}")
					if (vehiculo is Furgoneta) {
						Text(text = "Carga máxima: ${vehiculo.cargaMaxima}")
					}
					if (vehiculo is Trailer) {
						Text(text = "Carga máxima: ${vehiculo.cargaMaxima}")
					}
				}
			}
			if (showAll) {
				Icon(
					Icons.Rounded.KeyboardArrowUp,
					contentDescription = "Flecha que indica que se puede expandir",
				)
			} else {
				Icon(
					Icons.Rounded.KeyboardArrowDown,
					contentDescription = "Flecha que indica que se puede contraer",
				)
			}
		}
	}
}

/**
 * Formulario para crear un vehículo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormVehiculo(vehiculoType: VehiculoType, onFormCompleted: (Vehiculo) -> Unit) {
	var numRuedas by remember { mutableStateOf("") }
	var motor by remember { mutableStateOf("") }
	var numAsientos by remember { mutableStateOf("") }
	var color by remember { mutableStateOf("") }
	var modelo by remember { mutableStateOf("") }
	var cargaMaxima by remember { mutableStateOf("") }
	Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
		// TextFields para introducir los datos del vehículo
		OutlinedTextField(
			modifier = Modifier
				.fillMaxWidth(),
			value = numRuedas,
			onValueChange = { it ->
				val filteredText = it.filter { it.isDigit() }
				numRuedas = filteredText
			},
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number,
				imeAction = ImeAction.Next
			),
			label = { Text("Número de ruedas") }
		)
		OutlinedTextField(
			modifier = Modifier
				.fillMaxWidth(),
			value = motor,
			onValueChange = { motor = it },
			keyboardOptions = KeyboardOptions.Default.copy(
				imeAction = ImeAction.Next
			),
			label = { Text("Motor") }
		)
		if (vehiculoType != VehiculoType.PATINETE) {
			OutlinedTextField(
				modifier = Modifier
					.fillMaxWidth(),
				value = numAsientos,
				onValueChange = { it ->
					val filteredText = it.filter { it.isDigit() }
					numAsientos = filteredText
				},
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Number,
					imeAction = ImeAction.Next
				),
				label = { Text("Número de asientos") }
			)
		}
		OutlinedTextField(
			modifier = Modifier
				.fillMaxWidth(),
			value = color,
			onValueChange = { color = it },
			keyboardOptions = KeyboardOptions.Default.copy(
				imeAction = ImeAction.Next
			),
			label = { Text("Color") }
		)
		OutlinedTextField(
			modifier = Modifier
				.fillMaxWidth(),
			value = modelo,
			onValueChange = { modelo = it },
			keyboardOptions = KeyboardOptions.Default.copy(
				imeAction = ImeAction.Next
			),
			label = { Text("Modelo") }
		)
		if (vehiculoType == VehiculoType.FURGONETA || vehiculoType == VehiculoType.TRAILER) {
			OutlinedTextField(
				modifier = Modifier
					.fillMaxWidth(),
				value = cargaMaxima,
				onValueChange = { it ->
					val filteredText = it.filter { it.isDigit() }
					cargaMaxima = filteredText
				},
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Number,
					imeAction = ImeAction.Next
				),
				label = { Text("Carga máxima") }
			)
		}
		// Botón para crear el vehículo
		FilledTonalButton(
			modifier = Modifier
				.fillMaxWidth(),
			contentPadding = PaddingValues(vertical = 16.dp),
			onClick = {
				// Dependiendo del tipo de vehículo se comprueban unos campos u otros
				when (vehiculoType) {
					VehiculoType.COCHE -> {
						if (numRuedas.isNotBlank() && motor.isNotBlank() && numAsientos.isNotBlank() && color.isNotBlank() && modelo.isNotBlank()) {
							val coche = Coche(
								numRuedas = numRuedas.toInt(),
								motor = motor,
								numAsientos = numAsientos.toInt(),
								color = color,
								modelo = modelo
							)
							onFormCompleted(coche)
						}
					}

					VehiculoType.MOTO -> {
						if (numRuedas.isNotBlank() && motor.isNotBlank() && numAsientos.isNotBlank() && color.isNotBlank() && modelo.isNotBlank()) {
							val moto = Moto(
								numRuedas = numRuedas.toInt(),
								motor = motor,
								numAsientos = numAsientos.toInt(),
								color = color,
								modelo = modelo
							)
							onFormCompleted(moto)
						}
					}

					VehiculoType.PATINETE -> {
						if (numRuedas.isNotBlank() && motor.isNotBlank() && color.isNotBlank() && modelo.isNotBlank()) {
							val patinete = Patinete(
								numRuedas = numRuedas.toInt(),
								motor = motor,
								color = color,
								modelo = modelo
							)
							onFormCompleted(patinete)
						}
					}

					VehiculoType.FURGONETA -> {
						if (numRuedas.isNotBlank() && motor.isNotBlank() && numAsientos.isNotBlank() && color.isNotBlank() && modelo.isNotBlank() && cargaMaxima.isNotBlank()) {
							val furgoneta = Furgoneta(
								numRuedas = numRuedas.toInt(),
								motor = motor,
								numAsientos = numAsientos.toInt(),
								color = color,
								modelo = modelo,
								cargaMaxima = cargaMaxima.toInt()
							)
							onFormCompleted(furgoneta)
						}
					}

					VehiculoType.TRAILER -> {
						if (numRuedas.isNotBlank() && motor.isNotBlank() && numAsientos.isNotBlank() && color.isNotBlank() && modelo.isNotBlank() && cargaMaxima.isNotBlank()) {
							val trailer = Trailer(
								numRuedas = numRuedas.toInt(),
								motor = motor,
								numAsientos = numAsientos.toInt(),
								color = color,
								modelo = modelo,
								cargaMaxima = cargaMaxima.toInt()
							)
							onFormCompleted(trailer)
						}
					}
				}
			}) {
			Text(
				fontSize = 16.sp,
				text = "Crear"
			)
		}
	}

}

/**
 * Dialogo que pregunta por el tipo de vehículo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskVehiculoType(showDialog: Boolean, onTextFieldEntered: (Int) -> Unit) {
	var textFieldValue by remember { mutableStateOf("") }
	var show by remember { mutableStateOf(showDialog) }

	if (show) {
		AlertDialog(
			onDismissRequest = {
				show = false
				onTextFieldEntered(0)
			},
			confirmButton = {
				FilledTonalButton(
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
				Column {
					Text(text = "Introduzca el tipo de vehículo")
					Text(text = "1. Coche")
					Text(text = "2. Moto")
					Text(text = "3. Patinete")
					Text(text = "4. Furgoneta")
					Text(text = "5. Trailer")
					OutlinedTextField(
						value = textFieldValue,
						onValueChange = { it ->
							val filteredText = it.filter { it.isDigit() }
							textFieldValue = filteredText
						},
						keyboardOptions = KeyboardOptions.Default.copy(
							keyboardType = KeyboardType.Number
						),
						label = { Text("Opción") }
					)
				}
			}
		)
	}
}

/**
 * TopAppBar con el título de la aplicación
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("PR402")
		},
		colors = TopAppBarDefaults.smallTopAppBarColors(
			//container color materialTheme bckground primary
			containerColor = MaterialTheme.colorScheme.primary,
			titleContentColor = MaterialTheme.colorScheme.onPrimary,
		),
	)
}

@Preview(showBackground = true)
@Composable
fun ContainerPreview() {
	PR402Theme {
		ViewContainer()
	}
}