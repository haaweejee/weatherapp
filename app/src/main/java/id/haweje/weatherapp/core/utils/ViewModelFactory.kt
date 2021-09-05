package id.haweje.weatherapp.core.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.haweje.weatherapp.MainViewModel
import id.haweje.weatherapp.core.di.Injection
import id.haweje.weatherapp.core.source.domain.usecase.WeatherUseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val weatherUseCase: WeatherUseCase) : ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideWeatherUseCase(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(weatherUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}