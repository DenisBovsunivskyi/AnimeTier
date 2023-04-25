package com.denisbovsunivskyi.animetier.presentation.di

import com.denisbovsunivskyi.animetier.BuildConfig
import com.denisbovsunivskyi.animetier.data.api.AnimeServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor { chain ->
                chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                    .let(chain::proceed)
            }
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()
        println(BuildConfig.BASE_URL)
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAnimeApiService(retrofit: Retrofit):AnimeServiceApi{
        return retrofit.create(AnimeServiceApi::class.java)
    }
}