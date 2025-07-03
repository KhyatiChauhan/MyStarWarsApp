package com.example.mystarwarsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://swapi.info/api/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("{type}/")
    suspend fun getCategory(@Path("type") type: String): List<Map<String, Any>>
}

object SwapiApi{
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}