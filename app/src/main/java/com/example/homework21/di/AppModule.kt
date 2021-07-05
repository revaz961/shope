package com.example.homework21.di

import com.example.homework21.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    companion object {
        const val BASE_URL = "https://ktorhighsteaks.herokuapp.com/"
    }

    @Provides
    @Singleton
    fun provideService() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideAuthorizeRepository(apiService: ApiService): AuthorizeRepository =
        AuthorizeRepositoryImpl(apiService)
}