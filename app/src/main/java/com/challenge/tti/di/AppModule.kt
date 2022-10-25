package com.challenge.tti.di

import android.app.Application
import com.challenge.tti.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: App) {
    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }
}