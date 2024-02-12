package com.sebas.ac701

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebas.ac701.ui.theme.AC701Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AC701Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					GreetingImage()
				}
			}
		}
	}
}

@Composable
fun GreetingImage() {
	val image = painterResource(R.drawable.androidparty)
	val message = stringResource(R.string.happy_birthday_text)
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = androidx.compose.ui.Alignment.Center
	) {
		Image(
			painter = image,
			contentDescription = "Android Party",
			modifier = Modifier.fillMaxSize(),
			contentScale = androidx.compose.ui.layout.ContentScale.Crop
		)
		Text(
			text = message,
			textAlign = TextAlign.Center,
			fontSize = 50.sp,
		)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	AC701Theme {
		GreetingImage()
	}
}