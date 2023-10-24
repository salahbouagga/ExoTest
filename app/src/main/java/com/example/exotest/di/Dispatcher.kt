package com.example.exotest.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcher = module {

    single { Dispatchers.IO }
}
