package id.haweje.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haweje.weatherapp.core.source.WeatherRepository
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeatherData() : LiveData<WeatherResponse> = weatherRepository.getWeatherData()
}