package com.manuelsebastian.pr306

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelsebastian.pr306.ui.theme.PR306Theme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR306Theme {
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
fun Content(modifier: Modifier) {
	var day by remember { mutableStateOf("") }
	var month by remember { mutableStateOf("") }
	var year by remember { mutableStateOf("") }
	var result by remember { mutableStateOf("") }
	Column(modifier = modifier.padding(16.dp)) {
		OutlinedTextField(
			value = day,
			onValueChange = { day = it },
			label = { Text("Día") },
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number
			),
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		)

		OutlinedTextField(
			value = month,
			onValueChange = { month = it },
			label = { Text("Mes") },
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number,
			),
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		)

		OutlinedTextField(
			value = year,
			onValueChange = { year = it },
			label = { Text("Año") },
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number,
			),
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		)

		Button(
			onClick = {
				result = calculateDaysRemaining(day, month, year)
			},
			modifier = Modifier
				.fillMaxWidth()
				.height(60.dp)
				.padding(vertical = 8.dp)
		) {
			Text("Calcular")
		}

		if (result.isNotEmpty()) {
			Text("Días restantes: $result", modifier = Modifier.padding(vertical = 8.dp))
		}
	}
}

fun calculateDaysRemaining(day: String, month: String, year: String): String {
	try {
		val inputDate = LocalDate.of(year.toInt(), month.toInt(), day.toInt())
		val lastDayOfMonth = inputDate.lengthOfMonth()
		val daysRemaining = lastDayOfMonth - inputDate.dayOfMonth

		return daysRemaining.toString()
	} catch (e: Exception) {
		return "Datos incorrectos, introduce una fecha válida."
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("PR306")
		},
		colors = TopAppBarDefaults.smallTopAppBarColors(
			containerColor = MaterialTheme.colorScheme.primary,
			titleContentColor = MaterialTheme.colorScheme.onPrimary,
		),
	)
}

@Preview(showBackground = true)
@Composable
fun ViewContainerPreview() {
	PR306Theme {
		ViewContainer()
	}
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ViewContainerPreviewDark() {
	PR306Theme {
		ViewContainer()
	}
}