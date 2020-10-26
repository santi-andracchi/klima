package com.testapp.klima

import android.app.Application
import com.testapp.klima.datasource.location.IPLocationDataSource
import com.testapp.klima.datasource.location.LocationDataSource
import com.testapp.klima.mvvm.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class KlimaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val vmModule = module {

            // single instance of HelloRepository
            single<LocationDataSource> { IPLocationDataSource() }

            // Simple Presenter Factory
            factory { HomeViewModel(get()) }
        }

        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@KlimaApplication)
            modules(vmModule)
        }
    }
}