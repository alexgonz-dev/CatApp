package com.catapp.api.data.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val SERVER_URL = "https://cataas.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {

        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS) // Read timeout
            .connectTimeout(30, TimeUnit.SECONDS) // Connect timeout
            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideTaskApiService(retrofit: Retrofit): ItemService {
        return retrofit.create(ItemService::class.java)
    }
}