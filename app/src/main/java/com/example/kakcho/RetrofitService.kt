package com.example.kakcho

import com.example.kakcho.models.SearchIconResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {

    @GET("icons/search")
    suspend fun searchIcons(
        @Query("query") query: String,
        @Query("count") count: Int = 10,
        @Header("Authorization") auth: String = "Bearer oCOcJOIKYCStPYXeIkuJA8pVqINLPuoqXhqOmm6QL91tIKEvpnL0rzjA9F7m2aYA"
    ): Response<SearchIconResponse>

    companion object {
        private lateinit var retrofitService: RetrofitService
        fun getInstance(): RetrofitService {
            if (!::retrofitService.isInitialized) {
                val httpLoggingInterceptor =
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                val client = OkHttpClient.Builder().apply {
                    addInterceptor(httpLoggingInterceptor)
                }.build()

                val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://api.iconfinder.com/v4/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService
        }

    }
}