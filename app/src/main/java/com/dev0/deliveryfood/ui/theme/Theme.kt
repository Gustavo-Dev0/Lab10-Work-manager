package com.dev0.deliveryfood.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dev0.deliveryfood.core.data.repository.Theme

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val BlackLightColorPalette = lightColors(
    //primary = primaryLightColorBlack,
    primary = primaryDarkColorBlack,
    primaryVariant = primaryColorBlack,
    //secondary = secondaryLightColorBlack,
    secondary = secondaryDarkColorBlack,
    background = secondaryColorBlack,

    onPrimary = primaryTextColorBlack,
    onSecondary = secondaryTextColorBlack,

    surface = primaryColorBlack,
    //onBackground = Color.Black,
    onSurface =  primaryTextColorBlack,
)

private val BlackDarkColorPalette = lightColors(
    primary = primaryDarkColorBlack,
    primaryVariant = primaryColorBlack,
    secondary = secondaryDarkColorBlack,
    background = secondaryColorBlack,

    onPrimary = primaryTextColorBlack,
    onSecondary = secondaryTextColorBlack,
)




private val PinkLightColorPalette = lightColors(
    primary = primaryLightColorPink,
    primaryVariant = primaryColorPink,
    secondary = secondaryColorPink,
    background = secondaryLightColorPink,

    onPrimary = primaryTextColorPink,
    onSecondary = secondaryTextColorPink,

    surface = primaryColorPink,
    //onBackground = Color.Black,
    onSurface =  primaryTextColorPink,
)

private val PinkDarkColorPalette = lightColors(
    primary = primaryDarkColorPink,
    primaryVariant = primaryColorPink,
    secondary = secondaryDarkColorPink,
    background = secondaryColorPink,

    onPrimary = primaryTextColorPink,
    onSecondary = secondaryTextColorPink,
)




//
@Composable
fun DeliveryFoodTheme(darkTheme: Boolean = false, appTheme: String, content: @Composable () -> Unit) {

    val colors: Colors

    when (appTheme) {
        Theme.DEFAULT -> {
            colors = if (darkTheme) {
                DarkColorPalette
            } else {
                LightColorPalette
            }
        }
        Theme.BLACK -> {
            colors = if (darkTheme) {
                BlackDarkColorPalette
            } else {
                BlackLightColorPalette
            }
        }
        Theme.PINK -> {
            colors = if (darkTheme) {
                PinkDarkColorPalette
            } else {
                PinkLightColorPalette
            }
        }
        else -> colors = LightColorPalette

    }


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}