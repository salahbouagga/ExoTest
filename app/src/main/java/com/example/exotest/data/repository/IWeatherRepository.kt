package com.example.exotest.data.repository

import android.content.Context
import com.example.exotest.data.api.WeatherService
import com.example.exotest.data.model.Weather
import com.example.exotest.data.model.WeatherData
import com.example.exotest.utils.Constants
import com.example.exotest.utils.service.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
interface IWeatherRepository {
    suspend fun fetchWeatherByCity(city: String) : Flow<Resource<WeatherData>>


}
class WeatherRepository(private val api : WeatherService,
                        private val context : Context,
                        dispatcher : CoroutineDispatcher
):IWeatherRepository{

    override suspend fun fetchWeatherByCity(city: String): Flow<Resource<WeatherData>> = flow{
        emit(Resource.loading())
        try {
            val filter = "weather?q=${city}&appid=3b19fbe10c009ec62b62ddbfc1a1de2c"
            emit(Resource.success(api.getWeatherByCity(Constants.WEATHER_URL+filter)))
        }catch (err: Exception){
            println("err $err")

        }
    }

}