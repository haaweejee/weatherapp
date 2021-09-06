package id.haweje.weatherapp.core.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.haweje.weatherapp.MainViewModel
import id.haweje.weatherapp.core.di.Injection
import id.haweje.weatherapp.core.source.WeatherRepository
import id.haweje.weatherapp.core.source.domain.usecase.WeatherUseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val weatherRepository: WeatherRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(weatherRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}