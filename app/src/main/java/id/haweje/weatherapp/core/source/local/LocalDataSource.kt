package id.haweje.weatherapp.core.source.local

import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.local.room.WeatherDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val mWeatherDao: WeatherDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(weatherDao: WeatherDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(weatherDao)
    }

    fun getWeatherData(): Flow<WeatherEntity> = mWeatherDao.getWeatherData()

    suspend fun insertWeatherData(weather: WeatherEntity) = mWeatherDao.insertWeatherData(weather)


}