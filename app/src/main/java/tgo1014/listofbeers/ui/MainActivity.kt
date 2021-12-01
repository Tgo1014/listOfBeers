package tgo1014.listofbeers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import tgo1014.listofbeers.ui.screens.home.HomeScreen
import tgo1014.listofbeers.ui.theme.ListOfBeersTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListOfBeersTheme {
                HomeScreen()
            }
        }
    }
}