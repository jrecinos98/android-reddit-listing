package com.challenge.backend.di

import com.challenge.backend.Constants
import com.challenge.backend.api.RedditService
import com.challenge.backend.stores.RemoteListingDataStoreImpl
import com.challenge.domain.stores.listings.RemoteListingDataStore
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Dagger Module internal to the backend module. Any instance that is to be exposed will
 * be exposed by the Backend Component
 */
//Required annotation to prevent build time error with Hilt. Migration to Hilt Pending
@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi() : Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(
            HttpLoggingInterceptor{
                Timber.tag("Http").d(it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Provides
    fun provideRedditService(client : OkHttpClient, moshi : Moshi) : RedditService{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //TODO set as Build property
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
        return retrofit.create(RedditService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteListingDataStore(
        remoteStore : RemoteListingDataStoreImpl
    ) : RemoteListingDataStore {
        return remoteStore
    }
}