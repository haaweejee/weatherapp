package id.haweje.weatherapp.core.source.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object WeatherApiClient {
       fun getApiService() : WeatherApi {
           val retrofit = Retrofit.Builder()
               .baseUrl("https://api.openweathermap.org/data/2.5/")
               .addConverterFactory(MoshiConverterFactory.create())
               .client(getLoggingIntreceptor())
               .build()
           return retrofit.create(WeatherApi::class.java)
       }

    fun getLoggingIntreceptor() : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}