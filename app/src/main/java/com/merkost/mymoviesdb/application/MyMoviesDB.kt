package com.merkost.mymoviesdb.application

import android.app.Application
import com.merkost.mymoviesdb.di.application
import com.merkost.mymoviesdb.di.mainActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyMoviesDB : Application() {

        override fun onCreate() {
            super.onCreate()
            startKoin {
                // Koin Android logger
                androidLogger()
                //inject Android context
                androidContext(this@MyMoviesDB)
                modules(
                    listOf(
                        application,
                        mainActivity
                    )
                )
            }
        }
    }