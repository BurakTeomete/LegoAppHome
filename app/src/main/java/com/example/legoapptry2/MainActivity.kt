package com.example.legoapptry2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.legoapptry2.navigation.AppNavigation
import com.example.legoapptry2.ui.theme.LegoAppTry2Theme
import com.example.legoapptry2.ui.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LegoAppTry2Theme {
                AppNavigation()
            }
        }
    }
}

