package com.example.exotest.data.api

import com.example.exotest.data.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherService {

    @GET
    suspend fun getWeatherByCity(@Url URL:String): WeatherData
}