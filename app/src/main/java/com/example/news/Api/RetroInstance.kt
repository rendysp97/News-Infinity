package com.example.news.Api


import com.example.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {

    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val API_KEY = BuildConfig.API_KEY

    private val retrofit: Retrofit by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Intercept(API_KEY))
            .build()


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: Service by lazy {
        retrofit.create(Service::class.java)
    }

}


class Intercept(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $apiKey")
            .build()
        return chain.proceed(newRequest)
    }
}