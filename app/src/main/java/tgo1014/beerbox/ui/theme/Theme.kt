package tgo1014.beerbox.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GreenishBlue,
    secondary = Yellow,
    onSecondary = Color.Black,
    onPrimary = TypographyGray,
    surface = GreenishBlue,
    onSurface = TypographyGray,
    background = GreenishBlue,
    onBackground = TypographyGray
)

private val LightColorScheme = lightColorScheme(
    primary = GreenishBlue,
    secondary = Yellow,
    onSecondary = Color.Black,
    onPrimary = TypographyGray,
    surface = GreenishBlue,
    onSurface = TypographyGray,
    background = GreenishBlue,
    onBackground = TypographyGray
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BeerBoxTheme(
    darkTheme: Boolean = true,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}