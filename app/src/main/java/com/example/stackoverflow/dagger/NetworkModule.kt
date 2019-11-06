package com.example.stackoverflow.dagger

import com.example.stackoverflow.Constants
import com.example.stackoverflow.network.GetDataService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getDataService(retrofit: Retrofit): GetDataService {
        return retrofit.create(GetDataService::class.java)
    }
}