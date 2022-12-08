package com.example.jetweatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweatherforcast.screens.about.AboutScreen
import com.example.jetweatherforcast.screens.favorite.FavoriteScreen
import com.example.jetweatherforcast.screens.main.MainScreen
import com.example.jetweatherforcast.screens.main.MainViewModel
import com.example.jetweatherforcast.screens.search.SearchScreen
//import com.example.jetweatherforcast.screens.setting.SettingScreen
import com.example.jetweatherforcast.screens.setting.SettingsScreen
import com.example.jetweatherforcast.screens.setting.SettingsViewModel
import com.example.jetweatherforcast.screens.splash.WeatherSplashScreen


@Composable
    fun WeatherNavigation(){
        val navController = rememberNavController()

        NavHost(navController = navController,
            startDestination = WeatherScreens.SplashScreen.name ){

            composable(WeatherScreens.SplashScreen.name){
                WeatherSplashScreen(navController = navController)
            }

            //www.google.com/city = "seattle"
            val route = WeatherScreens.MainScreen.name
            composable("$route/{city}",
            arguments = listOf(navArgument(name = "city"){
                type = NavType.StringType })
            ){  navBack ->
                navBack.arguments?.getString("city").let {city->
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    val settingsViewModel = hiltViewModel<SettingsViewModel>()
                    MainScreen( navController = navController,
                                mainViewModel = mainViewModel,
                                city = city)
                }
            }

            composable(WeatherScreens.SearchScreen.name){
                val mainViewModel = hiltViewModel<MainViewModel>()
                SearchScreen(navController = navController)
            }

            composable(WeatherScreens.FavoriteScreen.name){
                val mainViewModel = hiltViewModel<MainViewModel>()
                FavoriteScreen(navController = navController)
            }

            composable(WeatherScreens.AboutScreen.name){
                AboutScreen(navController = navController)
            }

            composable(WeatherScreens.SettingsScreen.name){
                SettingsScreen(navController = navController)
            }
        }
    }
