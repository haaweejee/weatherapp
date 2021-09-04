package id.haweje.weatherapp.core.source

import androidx.lifecycle.LiveData
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource

interface WeatherDataSource {

    fun getWeatherData() : LiveData<Resource<WeatherEntity>>

    fun insertData(weatherEntity: WeatherEntity)
}