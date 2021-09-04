package id.haweje.weatherapp.core.di

import android.content.Context
import id.haweje.weatherapp.core.source.WeatherRepository
import id.haweje.weatherapp.core.source.local.LocalDataSource
import id.haweje.weatherapp.core.source.local.room.WeatherDatabase
import id.haweje.weatherapp.core.source.remote.RemoteDataSource
import id.haweje.weatherapp.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context) : WeatherRepository{

        val database = WeatherDatabase.getInstance(context)
        val localData = LocalDataSource.getInstance(database.weatherDao())
        val remoteData = RemoteDataSource.getInstance()
        val appExecutors = AppExecutors()

        return WeatherRepository.getInstance(remoteData, localData, appExecutors)
    }
}