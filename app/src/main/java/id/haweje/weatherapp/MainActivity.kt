package id.haweje.weatherapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.utils.Resource
import id.haweje.weatherapp.core.utils.ViewModelFactory
import id.haweje.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        refreshLayout()
        showData()

    }

    private fun refreshLayout() {
        showData()
        binding.swipeRefreshLayout.setOnRefreshListener{
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefreshLayout.isRefreshing = false
                showData()
            },3000)
        }
    }

    private fun showData(){
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getWeatherData().observe(this@MainActivity, { weather ->
            if (weather != null){
                when(weather){
                    is Resource.Loading ->
                        Snackbar.make(binding.mainLayout, "Tunggu Sebentar", Snackbar.LENGTH_SHORT).show()
                    is Resource.Success -> {
                        getUpdateTime()
                        getData(weather)
                        Snackbar.make(binding.mainLayout, "Berhasil", Snackbar.LENGTH_SHORT).show()
                    }
                    is Resource.Error ->
                        Snackbar.make(binding.mainLayout, "Gagal Update", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun getData(result: Resource.Success<WeatherEntity>){
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
    }


    private fun getUpdateTime(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("EEEE LLLL yyyy HH:mm:ss aaa z")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        binding.updateTimeId.text = dateTime
    }

}
