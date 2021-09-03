package id.haweje.weatherapp.core.di

import id.haweje.weatherapp.core.source.WeatherRepository
import id.haweje.weatherapp.core.source.remote.RemoteDataSource

object Injection {
    fun provideRepository() : WeatherRepository{

        val remoteDataSource = RemoteDataSource.getInstance()
        return WeatherRepository.getInstance(remoteDataSource)
    }
}