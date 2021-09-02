package id.haweje.weatherapp.core.source.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApiClient {
   companion object{
       fun getApiService() : WeatherApi {
           val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
           val client = OkHttpClient.Builder()
               .addInterceptor(loggingInterceptor)
               .build()
           val retrofit = Retrofit.Builder()
               .baseUrl("https://api.openweathermap.org/data/2.5/")
               .addConverterFactory(MoshiConverterFactory.create())
               .client(client)
               .build()
           return retrofit.create(WeatherApi::class.java)
       }
   }
}