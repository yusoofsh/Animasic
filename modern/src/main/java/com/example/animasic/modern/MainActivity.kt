package com.example.animasic.modern

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animasic.modern.ui.theme.AnimasicTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AnimasicTheme {
        Surface(
          modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
          CenteredShapes()
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
  AnimasicTheme {
    CenteredShapes()
  }
}

@Composable
fun CenteredShapes() {
  var crossfade by remember { mutableStateOf(false) }
  var flip by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Crossfade(targetState = crossfade, label = "") { state ->
      when (state) {
        false -> TriangleShape(size = 200.dp)
        true -> CircleShape(size = 200.dp)
      }
    }
    FilledTonalButton(onClick = { crossfade = !crossfade }) {
      Text("CrossFade")
    }
    FilledTonalButton(onClick = { flip = !flip }) {
      Text("Flip")
    }
  }
}

@Composable
fun TriangleShape(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val trianglePath = Path().apply {
      moveTo(size.toPx() / 2.0f, 0f)
      lineTo(size.toPx(), size.toPx())
      lineTo(0f, size.toPx())
      close()
    }
    drawPath(trianglePath, color = Color.Red)
  }
}

@Composable
fun CircleShape(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    drawCircle(
      color = Color.Blue,
      center = Offset(size.toPx() / 2.0f, size.toPx() / 2.0f),
      radius = size.toPx() / 2.0f
    )
  }
}
