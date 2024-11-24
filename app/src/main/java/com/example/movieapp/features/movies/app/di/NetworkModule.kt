package com.example.movieapp.features.movies.app.di

import com.example.movieapp.features.movies.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMTE3ZTNkYjcyNzE1OWQ2OWRlNDk3OGZiYjBhMjFiMiIsIm5iZiI6MTczMjQ3ODc1NS45NTY1NjEsInN1YiI6IjY2MWRkNjBmMjBhZjc3MDE3ZDNkMzkwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RQr8RPNdaCBzafiejk7CyBSMGGPW8kcvaAfwgEesIaQ"
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }
}