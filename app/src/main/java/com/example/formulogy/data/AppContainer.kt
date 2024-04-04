package com.example.formulogy.data

import com.example.formulogy.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val f1Repository: F1Repository
}

class DefaultAppContainer:AppContainer{
    val BASE_URL = "https://api.openf1.org/v1/"
    val json = Json{
        this.ignoreUnknownKeys = true
        coerceInputValues = true
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    override val f1Repository: F1Repository by lazy {
        F1RepositoryImpl(retrofitService)
    }
}