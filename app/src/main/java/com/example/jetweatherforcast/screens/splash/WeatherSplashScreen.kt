package com.example.jetweatherforcast.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweatherforcast.R
import com.example.jetweatherforcast.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController) { 
    //Text(text = "Splash Screen")

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true, block ={
        scale.animateTo(targetValue = 0.9f,
                        animationSpec =  tween(800,
                                         easing = {OvershootInterpolator(8f).getInterpolation(it)}))

        delay(2000L)
        navController.navigate(WeatherScreens.MainScreen.name)
    } )
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value),
        border = BorderStroke(2.dp, color = Color.LightGray),
        color = Color.White,
        shape = CircleShape
    ) {
    Column(modifier = Modifier.padding(1.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        
        Image(painter = painterResource(id = R.drawable.sun), contentDescription = "Sunny Icon",
        modifier = Modifier.size(90.dp),
        contentScale = ContentScale.Fit)
        
        Text(text = "Find The Sun?",
        style = MaterialTheme.typography.h5,
        color = Color.LightGray)

    }
    }
}