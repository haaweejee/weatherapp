package id.haweje.weatherapp.core.utils

import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse

object DataMapper {
    fun mapResponsetoEntity(data : WeatherResponse?) = WeatherEntity(
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
}