package com.example.exotest.di

import com.example.exotest.data.repository.IWeatherRepository
import com.example.exotest.data.repository.WeatherRepository
import org.koin.dsl.module

val repository = module {

    single<IWeatherRepository> { WeatherRepository(get(), get(), get()) }




}
