package id.haweje.weatherapp

import androidx.lifecycle.MutableLiveData
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource

object DataDummy {

    fun getDummyWeather() = WeatherEntity(
        id = 1,
        name = "",
        country = "",
        temp = 0.0,
        tempMin = 0.0,
        tempMax = 0.0,
        humidity = 0,
        pressure = 0,
        speed = 0.0,
        weatherInfo = ""
    )

    fun getWeatherLiveData() : MutableLiveData<Resource<WeatherEntity>>{
        val weather = MutableLiveData<Resource<WeatherEntity>>()
        weather.value = Resource.success(getDummyWeather())

        return weather
    }
}