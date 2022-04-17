package com.example.appricottesttask.di

import com.example.appricottesttask.data.DetailCharacterApiClient
import com.example.appricottesttask.data.RickAndMortyApiClient
import com.example.appricottesttask.data.RickAndMortyApiService
import com.example.appricottesttask.data.RickAndMortyRepositoryImpl
import com.example.appricottesttask.domain.RickAndMortyRepository
import com.example.sevenwindsstudiotask.common.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideRickAndMortyApiClient(retrofit: Retrofit): RickAndMortyApiClient {
        return retrofit.create(RickAndMortyApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailCharacterApiClient(retrofit: Retrofit): DetailCharacterApiClient {
        return retrofit.create(DetailCharacterApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(api: RickAndMortyApiService): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(api)
    }
}