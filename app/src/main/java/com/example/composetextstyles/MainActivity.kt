package com.example.composetextstyles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetextstyles.ui.theme.ComposeTextStylesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTextStylesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextStylesDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TextStylesDemo(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    // Eigene Schriftart aus res/font/lobster.ttf
    val customFont = FontFamily(Font(R.font.lobster))

    Column(modifier = modifier.padding(16.dp)) {
        Text("1. Standardtext")

        Text("2. Roter Text", color = Color.Red)

        Text("3. Blauer Text", color = Color.Blue)

        Text(
            "4. Fett & Kursiv",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        )

        Text("5. Großer Text", fontSize = 24.sp)
        Text("5. Kleiner Text", fontSize = 12.sp)

        Text("6. Eigene Schriftart", fontFamily = customFont)

        Text(
            text = "7. Link zu Google",
            color = Color.Blue,
            modifier = Modifier.clickable {
                uriHandler.openUri("https://www.google.com")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextStylesPreview() {
    ComposeTextStylesTheme {
        TextStylesDemo()
    }
}
