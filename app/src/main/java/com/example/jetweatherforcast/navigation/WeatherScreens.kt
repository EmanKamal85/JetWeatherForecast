package com.example.jetweatherforcast.navigation

import okhttp3.Route

enum class WeatherScreens {
    SplashScreen,
    MainScreen,
    AboutScreen,
    FavoriteScreen,
    SearchScreen,
    SettingsScreen;

companion object {
    fun fromRoute(route: String?) : WeatherScreens =
        when(route?.substringAfter("/")){
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            AboutScreen.name -> AboutScreen
            FavoriteScreen.name -> FavoriteScreen
            SearchScreen.name -> SearchScreen
            SettingsScreen.name ->SettingsScreen
            null -> SplashScreen
            else -> throw IllegalArgumentException("Route $route")
        }

}
}