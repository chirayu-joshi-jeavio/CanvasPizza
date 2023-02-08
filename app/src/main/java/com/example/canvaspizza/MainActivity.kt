package com.example.canvaspizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.canvaspizza.ui.theme.CanvasPizzaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasPizzaTheme {
                PizzaMenu()
            }
        }
    }
}
