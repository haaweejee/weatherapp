package id.haweje.weatherapp.core.source

import androidx.lifecycle.LiveData
import id.haweje.weatherapp.core.source.remote.RemoteDataSource
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse

class WeatherRepository private constructor(private val remoteDataSource: RemoteDataSource){

    companion object{
        private var instance: WeatherRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): WeatherRepository =
            instance ?: synchronized(this){
                instance ?: WeatherRepository(remoteDataSource).apply { instance = this }
            }
    }

    fun getWeatherData() : LiveData<WeatherResponse> = remoteDataSource.getWeatherData()
}