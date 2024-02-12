package com.sebas.ac704

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sebas.ac704.ui.theme.AC704Theme

class MainActivity : ComponentActivity() {
	var sm: SensorManager? = null
	var sa: Sensor? = null
	var SEL: SensorEventListener? = null

	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		sm = getSystemService(SensorManager::class.java)
		sa = sm?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
		setContent {
			AC704Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Scaffold(
						topBar = {
							TopAppBar(
								title = { Text("Látigo") },
								colors = TopAppBarDefaults.topAppBarColors(
									containerColor = MaterialTheme.colorScheme.primary,
									titleContentColor = MaterialTheme.colorScheme.onPrimary,
								)
							)
						},
						content = { paddingValues ->
							val context = LocalContext.current
							var sensorValue by remember { mutableStateOf("") }
							var latigo by remember { mutableIntStateOf(0) }
							val myGreen = Color(ContextCompat.getColor(context, R.color.myGreen))
							val myBlue = Color(ContextCompat.getColor(context, R.color.myBlue))
							val myBlack = Color(ContextCompat.getColor(context, R.color.black))
							val imageVaquero = painterResource(R.drawable.vaquero)
							var backgroundColor by remember { mutableStateOf(myGreen) }
							Column(
								modifier = Modifier
									.padding(paddingValues)
									.fillMaxSize()
									.background(color = backgroundColor)
							) {
								Text(
									text = "Valor del sensor = $sensorValue",
									modifier = Modifier.padding(16.dp),
									style = MaterialTheme.typography.titleLarge,
									color = myBlack
								)
								Image(
									painter = imageVaquero,
									contentDescription = "Vaquero",
									modifier = Modifier
										.fillMaxWidth()
										.aspectRatio(1f)
								)
								if (SEL == null) {
									SEL = object : SensorEventListener {
										override fun onSensorChanged(event: SensorEvent) {
											val x = event.values[0]
											sensorValue = x.toString()
											if (x < -7 && latigo == 0) {
												latigo++
												backgroundColor = myGreen
											} else if (x > 7 && latigo == 1) {
												latigo++
												backgroundColor = myBlue
											}
											if (latigo == 2) {
												sonido(context)
												latigo = 0
											}
										}

										fun sonido(context: Context) {
											val mp: MediaPlayer =
												MediaPlayer.create(context, R.raw.latigo)
											mp.start()
										}

										override fun onAccuracyChanged(
											sensor: Sensor,
											accuracy: Int
										) {
										}
									}
									sm?.registerListener(SEL, sa, SensorManager.SENSOR_DELAY_NORMAL)
								}
							}
						},
					)
				}
			}
		}
	}

	override fun onStop() {
		sm?.unregisterListener(SEL)
		super.onStop()
	}

	override fun onDestroy() {
		sm?.unregisterListener(SEL)
		super.onDestroy()
	}
}