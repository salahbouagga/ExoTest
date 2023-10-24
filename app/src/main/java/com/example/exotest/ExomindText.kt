package com.example.exotest

import android.app.Application
import androidx.multidex.BuildConfig
import com.example.exotest.di.dispatcher
import com.example.exotest.di.network
import com.example.exotest.di.repository
import com.example.exotest.di.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ExomindTest : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExomindTest)
            modules(
                listOf(
                    network,
                    repository,
                    viewModel,
                    dispatcher
                )
            )
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}