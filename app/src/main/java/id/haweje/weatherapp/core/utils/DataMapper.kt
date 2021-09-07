package id.haweje.weatherapp.core.utils

import id.haweje.weatherapp.core.source.domain.model.Weather
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse

object DataMapper {
    fun mapResponsetoEntity(data: WeatherResponse?) = WeatherEntity(
        id = 0,
        name = data?.name,
        country = data?.country,
        temp = data?.main?.temp,
        tempMin = data?.main?.tempMin,
        tempMax = data?.main?.tempMax,
        humidity = data?.main?.humidity,
        pressure = data?.main?.pressure,
        speed = data?.wind?.speed,
        weatherInfo = data?.weather!![0].main
    )

    fun mapEntitiestoDomain(data: WeatherEntity?) = data?.let {
        Weather(
            id = it.id,
            name = data.name,
            country = data.country,
            temp = data.temp,
            tempMax = data.tempMin,
            tempMin = data.tempMin,
            humidity = data.humidity,
            pressure = data.pressure,
            speed = data.speed,
            weatherInfo = data.weatherInfo
        )
    }

    fun mapDomainToEntities(data: Weather) = Weather(
        id = data.id,
        name = data.name,
        country = data.country,
        temp = data.temp,
        tempMin = data.tempMin,
        tempMax = data.tempMax,
        humidity = data.humidity,
        pressure = data.pressure,
        speed = data.speed,
        weatherInfo = data.weatherInfo
    )
}