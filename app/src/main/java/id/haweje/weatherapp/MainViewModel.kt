package id.haweje.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haweje.weatherapp.core.source.remote.network.WeatherApiClient
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> = _weather


    init {
        getWeatherData()
    }

    private fun getWeatherData(){
        val weatherData = WeatherApiClient.getApiService().getWeatherData()
        weatherData.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful){
                    _weather.value = response.body()
                }else{
                    Log.e("Fail", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Fail", "onFailure: ${t.message.toString()}")
            }
        })
    }
}