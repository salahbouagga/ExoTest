package com.example.exotest.di

import com.example.exotest.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel { WeatherViewModel(get()) }

}