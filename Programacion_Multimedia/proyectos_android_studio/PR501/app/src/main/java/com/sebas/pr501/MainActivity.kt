package com.sebas.pr501

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sebas.pr501.ui.theme.PR501Theme
import com.sebas.pr501.viewmodels.CalculadoraHipotecaViewModel
import com.sebas.pr501.views.CalculadoraHipotecaView

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PR501Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					CalculadoraHipotecaView(CalculadoraHipotecaViewModel())
				}
			}
		}
	}
}