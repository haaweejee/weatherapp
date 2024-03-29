package id.haweje.weatherapp.core.source

import id.haweje.weatherapp.core.source.local.LocalDataSource
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.remote.RemoteDataSource
import id.haweje.weatherapp.core.source.remote.network.ApiResponse
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import id.haweje.weatherapp.core.utils.DataMapper
import id.haweje.weatherapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class WeatherRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IWeatherRepository {

    companion object {
        private var instance: WeatherRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource
        ): WeatherRepository =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository(remoteData, localData).apply {
                    instance = this
                }
            }
    }

    override fun getWeatherData(): Flow<Resource<WeatherEntity>> =
        object : NetworkBoundResource<WeatherEntity, WeatherResponse>() {
            override fun loadFromDB(): Flow<WeatherEntity> = localDataSource.getWeatherData()

            override fun shouldFetch(data: WeatherEntity?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<WeatherResponse>> =
                remoteDataSource.getWeatherData()

            override suspend fun saveCallResult(data: WeatherResponse) {
                val weather = DataMapper.mapResponsetoEntity(data)
                localDataSource.insertWeatherData(weather)
            }
        }.asFlow()
}

