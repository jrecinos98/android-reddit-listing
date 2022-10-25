package com.challenge.tti.di

import com.challenge.di.BaseModule
import com.challenge.tti.App
import com.challenge.tti.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, BaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun baseModule(module : BaseModule) : Builder
        fun build(): AppComponent
    }

    fun inject(app : App)
    fun inject(mainFragment: MainFragment)
}