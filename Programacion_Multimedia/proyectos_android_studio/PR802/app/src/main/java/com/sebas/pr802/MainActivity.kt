package com.sebas.pr802

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sebas.pr802.retrofit.MainViewModel
import com.sebas.pr802.ui.theme.PR802Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val viewModel: MainViewModel by viewModels()
		setContent {
			PR802Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					MainScreen(viewModel = viewModel)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(text = "PR802") },
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primary,
					titleContentColor = MaterialTheme.colorScheme.onPrimary,
				)
			)
		},
		content = {
			Content(modifier = modifier.padding(it), viewModel = viewModel)
		}
	)
}

@Composable
fun Content(modifier: Modifier = Modifier, viewModel: MainViewModel) {
	val marvelResponse by viewModel.marvelGetResponse
	LazyColumn(
		modifier = modifier
			.padding(16.dp)
	) {
		item {
			Text(text = "Héroes de Marvel")
		}
		items(marvelResponse.data.results) { hero ->
			Text(text = hero.name)
			val imageUrl = hero.thumbnail.path + "." + hero.thumbnail.extension
			AsyncImage(
				model = imageUrl,
				contentDescription = "Translated description of what the image contains"
			)
		}
	}
	DisposableEffect(Unit) {
		viewModel.getHeroes()
		onDispose {}
	}
}