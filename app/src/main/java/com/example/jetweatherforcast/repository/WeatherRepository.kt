package com.example.jetweatherforcast.repository

import android.util.Log
import com.example.jetweatherforcast.data.DataOrException
import com.example.jetweatherforcast.model.Weather
import com.example.jetweatherforcast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {


    suspend fun getWeather (cityQuery : String) : DataOrException<Weather, Boolean, Exception>{
        val response = try {
            api.getWeather(cityQuery)
        }catch (e : Exception){
            Log.d("REX", "getWeather: $e")
        return DataOrException(e = e)
        }

        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}