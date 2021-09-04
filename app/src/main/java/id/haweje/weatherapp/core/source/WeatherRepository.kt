package id.haweje.weatherapp.core.source

import androidx.lifecycle.LiveData
import id.haweje.weatherapp.core.source.local.LocalDataSource
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.remote.ApiResponse
import id.haweje.weatherapp.core.source.remote.RemoteDataSource
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import id.haweje.weatherapp.core.utils.AppExecutors
import id.haweje.weatherapp.core.utils.Resource

class WeatherRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) : WeatherDataSource{

    companion object{
        private var instance: WeatherRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): WeatherRepository =
            instance ?: synchronized(this){
                instance ?: WeatherRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getWeatherData(): LiveData<Resource<WeatherEntity>> {
        return object : NetworkBoundResource<WeatherEntity, WeatherResponse>(appExecutors){
            override fun loadFromDB(): LiveData<WeatherEntity> = localDataSource.getWeatherData()

            override fun shouldFetch(data: WeatherEntity?): Boolean = data == null


            override fun createCall(): LiveData<ApiResponse<WeatherResponse?>> = remoteDataSource.getWeatherData()


            override fun saveCallResult(data: WeatherResponse?) {
                with(data){
                    val weather = WeatherEntity(
                        id = 0,
                        name = this?.name,
                        country = this?.country,
                        temp = this?.main?.temp,
                        tempMin = this?.main?.tempMin,
                        tempMax = this?.main?.tempMax,
                        humidity = this?.main?.humidity,
                        pressure = this?.main?.pressure,
                        speed = this?.wind?.speed,
                        weatherInfo = this?.weather!![0].main
                    )
                    localDataSource.insertWeatherData(weather)
                }

            }
        }.asLiveData()
    }

    override fun insertData(weatherEntity: WeatherEntity) {
        val runnable = {
            if (localDataSource.getWeatherData().value == null){
                localDataSource.insertWeatherData(weatherEntity)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

}