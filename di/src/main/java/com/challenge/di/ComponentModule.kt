package com.challenge.di

import android.content.Context
import com.challenge.backend.di.BackendComponent
import com.challenge.backend.di.DaggerBackendComponent
import com.challenge.storage.di.DaggerStorageComponent
import com.challenge.storage.di.StorageComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ComponentModule {
    /**
     * Instance of BackendComponent that exposes all necessary
     * objects from the backend module
     */
    @Singleton
    @Provides
    fun providesBackendComponent() : BackendComponent {
        return DaggerBackendComponent.builder().build()
    }

    /**
     * Instance of StorageComponent that exposes all necessary
     * objects from the storage module
     */
    @Singleton
    @Provides
    fun providesStorageComponent(@ApplicationContext appContext: Context) : StorageComponent {
        return DaggerStorageComponent.factory().create(appContext)
    }
}