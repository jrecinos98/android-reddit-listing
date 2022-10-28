package com.challenge.tti.di

import com.challenge.di.BaseModule
import com.challenge.tti.App
import com.challenge.tti.ui.main.comments.CommentsFragment
import com.challenge.tti.ui.main.listings.ListingsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Main app component. Relies on BaseModule to provide Repository instance and other objects
 * required from modules that the app module does not directly depend on.
 */
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
    fun inject(listingsFragment: ListingsFragment)
    fun inject(commentsFragment: CommentsFragment)
}