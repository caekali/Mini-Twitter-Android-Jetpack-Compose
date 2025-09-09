package com.example.minitwitter.di

import android.content.Context
import com.example.minitwitter.data.local.AuthPreferences
import com.example.minitwitter.data.remote.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): UserApiService {
        return Retrofit.Builder()
            .baseUrl("https://82ac890a3eaf.ngrok-free.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideAuthPreferences(
        @ApplicationContext context: Context
    ): AuthPreferences {
        return AuthPreferences(context)
    }
}