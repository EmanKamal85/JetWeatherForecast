package com.example.jetweatherforcast.network

import com.example.jetweatherforcast.model.Weather
import com.example.jetweatherforcast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET("data/2.5/forecast/daily?")
    suspend fun getWeather(
       @Query("q") query : String,
        @Query("appid") appid : String = Constants.API_KEY,
        @Query("units") units : String = "imperial") : Weather


}