package com.sebas.ac602

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
import com.sebas.ac602.ui.theme.AC602Theme
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AC602Theme {
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
				title = { Text(text = "AC602") },
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
	val context = LocalContext.current
	var note by remember { mutableStateOf(readNoteFromFile(context)) }

	Column(
		modifier = modifier.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		OutlinedTextField(
			value = note,
			onValueChange = { note = it },
			label = { Text("Escribe una nota aquí...") },
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
		)

		FilledTonalButton(onClick = { saveNoteToFile(context, note) }) {
			Text("Guardar texto")
		}
	}
}

fun saveNoteToFile(context: Context, note: String) {
	val fos: FileOutputStream = context.openFileOutput("notas.txt", Context.MODE_PRIVATE)
	fos.write(note.toByteArray())
	fos.close()
}

fun readNoteFromFile(context: Context): String {
	val file = File(context.filesDir, "notas.txt")
	return if (file.exists()) {
		file.bufferedReader().use(BufferedReader::readText)
	} else {
		""
	}
}

@Preview(showBackground = true)
@Composable
fun ViewContainerPreview() {
	AC602Theme {
		ViewContainer()
	}
}