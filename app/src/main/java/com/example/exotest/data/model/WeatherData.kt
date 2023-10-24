package com.example.exotest.data.model

import com.google.gson.annotations.SerializedName

data class WeatherData(@SerializedName("weather") var weather: List<Weather>,
                       @SerializedName("main") var main:Main,
                       @SerializedName("clouds") var cloud:Cloud,
                       @SerializedName("name") var region: String)
