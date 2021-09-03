package id.haweje.weatherapp.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.haweje.weatherapp.MainViewModel
import id.haweje.weatherapp.core.di.Injection
import id.haweje.weatherapp.core.source.WeatherRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val weatherRepository: WeatherRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
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