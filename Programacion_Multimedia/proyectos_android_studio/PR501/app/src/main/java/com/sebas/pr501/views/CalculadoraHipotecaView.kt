package com.sebas.pr501.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebas.pr501.viewmodels.CalculadoraHipotecaViewModel
import kotlinx.coroutines.launch

@Composable
fun CalculadoraHipotecaView(viewModel: CalculadoraHipotecaViewModel) {
	Scaffold(
		topBar = { Toolbar() },
		content = { paddingValues ->
			Content(
				modifier = Modifier.padding(paddingValues),
				viewModel
			)
		},
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
	TopAppBar(
		title = {
			Text("Calculadora Hipoteca")
		},
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.primary,
			titleContentColor = MaterialTheme.colorScheme.onPrimary,
		),
	)
}

@Composable
fun Content(modifier: Modifier, viewModel: CalculadoraHipotecaViewModel) {
	val capital: String by viewModel.capital.observeAsState("")
	val plazo: String by viewModel.plazo.observeAsState("")
	val calculateEnable: Boolean by viewModel.calculateEnable.observeAsState(false)
	val cuota: Double by viewModel.cuota.observeAsState(0.0)
	val showCuota: Boolean by viewModel.showCuota.observeAsState(false)
	val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
	val courotineScope = rememberCoroutineScope()
	Column(
		modifier = modifier.padding(20.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
	) {
		CapitalField(capital) { viewModel.onTextFieldsChange(it, plazo) }
		PlazoField(plazo) { viewModel.onTextFieldsChange(capital, it) }
		CalculateButton(calculateEnable) {
			courotineScope.launch {
				viewModel.onCalculateClick()
			}
		}
		if (isLoading) {
			CircularProgressIndicator()
		}
		if (showCuota) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					style = MaterialTheme.typography.bodyMedium,
					text = "La cuota es de: "
				)
				Text(
					style = MaterialTheme.typography.titleLarge,
					fontSize = 40.sp,
					fontWeight = FontWeight.Bold,
					color = Green,
					text = "${String.format("%.2f", cuota)}€"
				)
			}

		}
	}
}

@Composable
fun CapitalField(capital: String, onTextFieldChange: (String) -> Unit) {
	OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = capital,
		onValueChange = { onTextFieldChange(it) },
		label = { Text(text = "Capital") },
		keyboardOptions = KeyboardOptions.Default.copy(
			keyboardType = KeyboardType.Number,
			imeAction = ImeAction.Next,
		),
		singleLine = true,
	)
}

@Composable
fun PlazoField(plazo: String, onTextFieldChange: (String) -> Unit) {
	OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = plazo,
		onValueChange = { onTextFieldChange(it) },
		label = { Text(text = "Plazo") },
		keyboardOptions = KeyboardOptions.Default.copy(
			keyboardType = KeyboardType.Number,
			imeAction = ImeAction.Done,
		),
		singleLine = true,
	)
}

@Composable
fun CalculateButton(calculateEnable: Boolean, onCalculateClick: () -> Unit) {
	FilledTonalButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 10.dp),
		onClick = { onCalculateClick() },
		enabled = calculateEnable,
		contentPadding = PaddingValues(vertical = 16.dp)
	) {
		Text(
			style = MaterialTheme.typography.titleLarge,
			text = "Calcular"
		)
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	CalculadoraHipotecaView(CalculadoraHipotecaViewModel())
}