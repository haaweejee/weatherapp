package id.haweje.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.haweje.weatherapp.core.source.domain.usecase.WeatherUseCase

class MainViewModel(weatherUseCase: WeatherUseCase) : ViewModel() {

    val weather = weatherUseCase.getWeatherData().asLiveData()
}