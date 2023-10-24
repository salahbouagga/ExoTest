package com.example.exotest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.exotest.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val btnCheckWeather = findViewById<Button>(R.id.btn_check_weather)
        btnCheckWeather.setOnClickListener {
            checkWeather()
        }
    }

    private fun checkWeather(){
        val intent = Intent(this@MainActivity, WeatherActivity::class.java)
        startActivity(intent)
        finish()
    }
}

