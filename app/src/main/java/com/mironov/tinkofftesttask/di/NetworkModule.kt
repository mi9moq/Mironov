package com.mironov.tinkofftesttask.di

import com.mironov.tinkofftesttask.data.remote.api.FilmApi
import com.mironov.tinkofftesttask.data.remote.api.KeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    companion object {

        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    }

    @AppScope
    @Provides
    fun provideHttpClient(keyInterceptor: KeyInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(keyInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    @AppScope
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @AppScope
    @Provides
    fun provideFilmApi(retrofit: Retrofit): FilmApi = retrofit.create()
}