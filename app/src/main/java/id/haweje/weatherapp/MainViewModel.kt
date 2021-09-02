package id.haweje.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haweje.weatherapp.core.source.remote.network.WeatherApiClient
import id.haweje.weatherapp.core.source.remote.response.WeatherResponse
import id.haweje.weatherapp.utils.Constanta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

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
                    Timber.tag(Constanta.SUCCESS).d("Success load from API:%s", response.message())
                }else {
                    Timber.tag(Constanta.FAIL).e("onFailure :%s", response.message())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Timber.tag(Constanta.FAIL).e("onFailure :%s", t.message)
            }
        })
    }
}