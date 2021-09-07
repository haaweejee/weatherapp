package id.haweje.weatherapp.core.source.remote

import id.haweje.weatherapp.core.source.remote.network.ApiResponse
import id.haweje.weatherapp.core.source.remote.network.WeatherApi
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

data class RemoteDataSource constructor(private val api: WeatherApi) {

    suspend fun getWeatherData(): Flow<ApiResponse<WeatherResponse>> {
        //get data From remote api
        return flow {
            try {
                val response = api.getWeatherData()
                emit(ApiResponse.Success(response))
                Timber.d("onSuccess $response")
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e("onError$e")
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(api: WeatherApi): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(api)
            }
    }
}



