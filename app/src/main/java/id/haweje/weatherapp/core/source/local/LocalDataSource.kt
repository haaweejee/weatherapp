package id.haweje.weatherapp.core.source.local

import androidx.lifecycle.LiveData
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.local.room.WeatherDao

class LocalDataSource private constructor(private val mWeatherDao: WeatherDao){

    companion object{
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(weatherDao: WeatherDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(weatherDao)
    }

    fun getWeatherData() : LiveData<WeatherEntity> = mWeatherDao.getWeatherData()

    fun insertWeatherData(weather : WeatherEntity) = mWeatherDao.insertWeatherData(weather)


}