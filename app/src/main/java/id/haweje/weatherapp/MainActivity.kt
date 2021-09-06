package id.haweje.weatherapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource
import id.haweje.weatherapp.core.utils.StatusResponse
import id.haweje.weatherapp.core.utils.ViewModelFactory
import id.haweje.weatherapp.databinding.ActivityMainBinding
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        refreshLayout()
        showData()

        Timber.d("Aplikasi Berjalan")


    }

    private fun refreshLayout() {
        showData()
        binding.swipeRefreshLayout.setOnRefreshListener{
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefreshLayout.isRefreshing = false
                showData()
            },1000)
        }
    }

    private fun showData(){
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getWeatherData().observe(this@MainActivity, { weather ->
            if (weather != null){
                when(weather.status){
                    StatusResponse.SUCCESS -> {
                        getData(weather)
                        Snackbar.make(binding.mainLayout, "Berhasil", Snackbar.LENGTH_SHORT).show()
                    }
                    StatusResponse.ERROR -> Snackbar.make(binding.mainLayout, "Gagal", Snackbar.LENGTH_SHORT).show()
                    StatusResponse.LOADING -> Snackbar.make(binding.mainLayout, "Please wait", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun getData(result: Resource<WeatherEntity>){
        val weatherTemp = result.data?.temp
        val celcius = (weatherTemp?.div(10))?.toInt()
        binding.apply {
            temperatureId.text = String.format(getString(R.string.temp), celcius.toString())
            maxTemperatureId.text = String.format(getString(R.string.max_temp), result.data?.tempMax?.toString())
            minTemperatureId.text = String.format(getString(R.string.min_temp), result.data?.tempMin?.toString())
            cityNameId.text = result.data?.name
            humidityId.text = result.data?.humidity.toString()
            pressureId.text = result.data?.pressure.toString()
            windId.text = result.data?.speed.toString()
            weatherConditionId.text = result.data?.weatherInfo
        }
        getUpdateTime()
    }


    private fun getUpdateTime(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("EEEE LLLL yyyy HH:mm:ss aaa z")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        binding.updateTimeId.text = dateTime
    }

}
