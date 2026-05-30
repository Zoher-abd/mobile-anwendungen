package com.example.composecolorbars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecolorbars.ui.theme.ComposeColorBarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeColorBarsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    EbuColorBars()
                }
            }
        }
    }
}

@Composable
fun EbuColorBars() {
    val colors = listOf(
        Color(0xFFFFFFFF), // Weiß
        Color(0xFFFF0000), // Rot
        Color(0xFF00FF00), // Grün
        Color(0xFF0000FF), // Blau
        Color(0xFFFFFF00), // Gelb
        Color(0xFF00FFFF), // Cyan
        Color(0xFFFF00FF), // Magenta
        Color(0xFF000000)  // Schwarz
    )

    Column(modifier = Modifier.fillMaxSize()) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EbuColorBarsPreview() {
    ComposeColorBarsTheme {
        EbuColorBars()
    }
}
