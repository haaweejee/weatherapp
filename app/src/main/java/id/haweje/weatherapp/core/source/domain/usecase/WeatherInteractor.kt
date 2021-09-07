package id.haweje.weatherapp.core.source.domain.usecase

import id.haweje.weatherapp.core.source.IWeatherRepository
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(private val weatherRepository: IWeatherRepository) : WeatherUseCase {
    override fun getWeatherData(): Flow<Resource<WeatherEntity>> =
        weatherRepository.getWeatherData()
}