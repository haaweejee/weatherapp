package id.haweje.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.haweje.weatherapp.core.source.WeatherRepository
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeatherData(): LiveData<Resource<WeatherEntity>> =
        weatherRepository.getWeatherData().asLiveData()
}