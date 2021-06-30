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
        private const val BASE_URL = "https://ktorhighsteaks.herokuapp.com/"
    }

    @Singleton
    @Provides
    fun provideService() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideAuthorizeRepository(apiHelper: ApiHelperImpl): AuthorizeRepository =
        AuthorizeRepositoryImpl(apiHelper)
}