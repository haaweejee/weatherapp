package id.haweje.weatherapp.core.source.domain.usecase

import id.haweje.weatherapp.core.source.domain.model.Weather
import id.haweje.weatherapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {
    fun getWeatherData() : Flow<Resource<Weather>>
}