package com.sebas.ac601

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sebas.ac601.ui.theme.AC601Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "EMAIL")

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AC601Theme {
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
		topBar = {
			TopAppBar(
				title = { Text(text = "AC601") },
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primary,
					titleContentColor = MaterialTheme.colorScheme.onPrimary,
				)
			)
		},
		content = { paddingValues ->
			MainView(modifier = Modifier.padding(paddingValues))
		}
	)
}

@Composable
fun MainView(modifier: Modifier) {
	var email by remember { mutableStateOf("") }
	var buttonEnable by remember { mutableStateOf(false) }
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	val savedEmail: String by getEmail(context).collectAsState(initial = "")
	Column(
		modifier = modifier.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Text(text = "Email guardado antes de cerrar la app: $savedEmail")
		OutlinedTextField(
			value = email,
			onValueChange = {
				email = it
				buttonEnable = email.isNotEmpty()
			},
			label = { Text("Email") },
			keyboardOptions = KeyboardOptions.Default.copy(
				imeAction = ImeAction.Done
			),
		)
		FilledTonalButton(
			onClick = {
				scope.launch {
					saveEmail(email, context)
					email = ""
				}
			},
			enabled = buttonEnable
		) {
			Text(text = "Guardar")
		}
	}
}

fun getEmail(context: Context): Flow<String> {
	return context.dataStore.data
		.map { preferences ->
			preferences[stringPreferencesKey("email")] ?: ""
		}
}

suspend fun saveEmail(email: String, context: Context) {
	context.dataStore.edit { preferences ->
		preferences[stringPreferencesKey("email")] = email
	}
}

@Preview(showBackground = true)
@Composable
fun PReviewViewContainer() {
	AC601Theme {
		ViewContainer()
	}
}