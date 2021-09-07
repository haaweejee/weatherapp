package id.haweje.weatherapp.core.source.remote.network

import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("q") cityName: String = "Tangerang",
        @Query("appid") apiKey: String = "cc0c60551e68963a45c7cc6e22e3b06a"
    ): WeatherResponse
}