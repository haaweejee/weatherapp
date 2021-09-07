package id.haweje.weatherapp.core.source

import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {

    fun getWeatherData(): Flow<Resource<WeatherEntity>>

}