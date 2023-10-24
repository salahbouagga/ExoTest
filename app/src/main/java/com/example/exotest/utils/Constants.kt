package com.example.exotest.utils

import android.widget.Toast

class Constants {
    companion object{
        var WEATHER_URL = "https://api.openweathermap.org/data/2.5/"
        val FIRST_MESSAGE = "Nous téléchargeons les données…"
        val SECOND_MESSAGE = "C’est presque fini…"
        val THIRD_MESSAGE = "Plus que quelques secondes avant d’avoir le résultat…"
        val DURATION = Toast.LENGTH_LONG
    }
}