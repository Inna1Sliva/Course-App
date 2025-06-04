package com.it.shka.courseapp.dagger

import com.it.shka.courseapp.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModuls {
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://683eeb8c1cd60dca33dd922b.mockapi.io/")
            .build()
    }
    @Singleton
    @Provides
    fun providesAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}