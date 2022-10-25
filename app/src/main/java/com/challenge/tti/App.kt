package com.challenge.tti

import android.app.Application
import com.challenge.di.BaseModule
import com.challenge.tti.di.AppComponent
import com.challenge.tti.di.AppModule
import com.challenge.tti.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {
    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
        plantTimber()
    }

    private fun initDaggerAppComponent(): AppComponent {
        appComponent =
            DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .baseModule(BaseModule(this))
                .build()
        return appComponent
    }

    private fun plantTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}