package id.haweje.weatherapp.core.source.remote

import id.haweje.weatherapp.core.source.remote.network.ApiResponse
import id.haweje.weatherapp.core.source.remote.network.WeatherApi
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource private constructor(private val api: WeatherApi){

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(api: WeatherApi): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(api)
            }
    }


    suspend fun getWeatherData(): Flow<ApiResponse<WeatherResponse?>> {
        return flow {
            try {
                val response = api.getWeatherData()
                emit(ApiResponse.Success(response))
                Timber.d("Success Load")
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.e("Error", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}



