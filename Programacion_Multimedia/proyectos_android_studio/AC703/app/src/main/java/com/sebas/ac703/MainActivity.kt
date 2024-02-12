package com.sebas.ac703

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.*
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.sebas.ac703.ui.theme.AC703Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AC703Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					MainScreen()
				}
			}
		}
	}
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
	val context = LocalContext.current
	val exoPlayer = ExoPlayer.Builder(context).build()
	val videoUrl =
		"https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
	val videoItem = MediaItem.fromUri(videoUrl)
	val playerView = PlayerView(context)
	val playWhenReady by remember { mutableStateOf(true) }
	exoPlayer.setMediaItem(videoItem)
	playerView.player = exoPlayer
	LaunchedEffect(exoPlayer) {
		exoPlayer.prepare()
		exoPlayer.playWhenReady = playWhenReady
	}

	AndroidView(factory = {
		playerView
	})
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	AC703Theme {
		MainScreen()
	}
}