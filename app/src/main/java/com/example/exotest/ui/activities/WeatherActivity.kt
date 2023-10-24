package com.example.exotest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exotest.R
import com.example.exotest.data.model.WeatherData
import com.example.exotest.ui.adapter.WeatherAdapter
import com.example.exotest.ui.viewmodel.WeatherViewModel
import com.example.exotest.utils.Constants
import com.example.exotest.utils.service.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.ArrayList

class WeatherActivity : AppCompatActivity() {
    var weatherRV: RecyclerView? = null
    var currentProgress:Int = 0
    var progressbar: ProgressBar? = null
    var percentageProgress:Int = 0
    var state_text: TextView? = null
    var msg_text: TextView? = null
    var weatherList: ArrayList<WeatherData>? = ArrayList()
    val scope = MainScope()
    var job: Job? = null
    var btn_restart: Button? = null
    var displayElement:Boolean? = false

    private val viewModel: WeatherViewModel
        get() = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }

    override fun onStart() {
        super.onStart()
        progressbar = findViewById(R.id.progressbar)
        weatherRV = findViewById(R.id.recyclerView_weather)
        state_text = findViewById(R.id.state_text)
        msg_text = findViewById(R.id.text_msg)
        btn_restart = findViewById(R.id.btn_restart)
        refData()
        updatesDataCalls()
    }

    private fun getByCity(city: String){
        viewModel.fetchWeather(city)
    }

    private fun refData(){
        lifecycleScope.launch {
            viewModel._listWeather.collectLatest {
                    resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        weatherList?.add(resource.data!!)
                    }
                    Resource.Status.LOADING -> {
                        println("Loading...")
                    }
                    Resource.Status.ERROR -> {
                        println("Error...")
                    }
                    else->{
                    }
                }
            }
        }
    }

    fun updatesDataCalls() {
        currentProgress = 0
        stopUpdates()
        toastDisplay()
        job = scope.launch {
            while(true) {
                callByCity()
                showButton()
                state_text?.setText(percentageProgress.toString() + "%")
                progressbar?.setProgress(currentProgress)
                currentProgress = currentProgress+10
                delay(10000)
            }
        }
    }

    private fun showButton(){
        if (displayElement == true){
            progressbar?.visibility = View.GONE
            state_text?.visibility = View.GONE
            msg_text?.visibility = View.GONE
            btn_restart?.visibility =  View.VISIBLE
        }else if(displayElement == false){
            state_text?.visibility = View.VISIBLE
            msg_text?.visibility = View.VISIBLE
            progressbar?.visibility = View.VISIBLE
            btn_restart?.visibility =  View.GONE
        }
        if (btn_restart != null){
            btn_restart?.setOnClickListener {
                percentageProgress = 0
                weatherList?.clear()
                initWeatherRV(weatherList!!)
                displayElement = false
                updatesDataCalls()
            }
        }
    }

    fun stopUpdates() {
        job?.cancel()
        job = null
    }

    private fun toastDisplay(){
        val fm = Toast.makeText(applicationContext, Constants.FIRST_MESSAGE, Constants.DURATION)
        val sm = Toast.makeText(applicationContext, Constants.SECOND_MESSAGE, Constants.DURATION)
        val tm = Toast.makeText(applicationContext, Constants.THIRD_MESSAGE, Constants.DURATION)

        job = scope.launch {
            while(currentProgress < 60) {
                fm.show()
                delay(6000)
            }
        }
        job = scope.launch {
            while(currentProgress < 60) {
                sm.show()
                delay(6000)
            }
        }
        job = scope.launch {
            while(currentProgress < 60) {
                tm.show()
                delay(6000)
            }
        }
    }

    private fun callByCity(){
        if (currentProgress == 0 ){
            getByCity("Rennes")
        }else if(currentProgress==10){
            getByCity("Paris")
            percentageProgress = 33
        }else if (currentProgress==20){
            getByCity("Nantes")
            percentageProgress = 49
        }else if(currentProgress==30){
            getByCity("Bordeaux")
            percentageProgress = 65
        }else if(currentProgress == 40 ){
            getByCity("Lyon")
            percentageProgress = 71
        }else if(currentProgress == 60){
            initWeatherRV(weatherList!!)
            percentageProgress = 100
            displayElement = true
        }
    }

    private fun initWeatherRV(list : ArrayList<WeatherData>) {
        weatherRV?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WeatherAdapter(viewModel,list ?: ArrayList(),context)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@WeatherActivity, MainActivity::class.java))
        finish()
    }
}