package id.haweje.weatherapp.core.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.haweje.weatherapp.core.source.remote.network.WeatherApiClient
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import id.haweje.weatherapp.core.utils.Constanta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource {

    companion object{

        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource()
            }
    }

    fun getWeatherData() : LiveData<WeatherResponse>{
        val weatherResult = MutableLiveData<WeatherResponse>()
        val weatherData = WeatherApiClient.getApiService().getWeatherData()
        weatherData.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    weatherResult.value = response.body()
                    Timber.tag(Constanta.SUCCESS).d("Success load from API:%s", response.body())
                }else {
                    Timber.tag(Constanta.FAIL).e("onFailure :%s", response.message())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Timber.tag(Constanta.FAIL).e("onFailure :%s", t.message)
            }
        })
        return weatherResult
    }


    interface LoadWeatherDataCallback{
        fun onLoadWeather(data: WeatherResponse?)
    }
}



