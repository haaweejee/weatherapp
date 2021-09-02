package id.haweje.weatherapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.haweje.weatherapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private var timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        refreshLayout()
        showData()
    }

    private fun refreshLayout(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            showData()
            timer.schedule(500L){
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun showData(){
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        viewModel.weather.observe(this, { weather ->
            val weatherTemp = weather.main.temp
            val celcius = (weatherTemp / 10).toInt()
            binding.temperatureId.text = String.format(getString(R.string.temp), celcius.toString())
            binding.maxTemperatureId.text = String.format(getString(R.string.max_temp), weather.main.tempMax.toString())
            binding.minTemperatureId.text = String.format(getString(R.string.min_temp), weather.main.tempMin.toString())
            binding.cityNameId.text = weather.name
            binding.humidityId.text = weather.main.humidity.toString()
            binding.pressureId.text = weather.main.pressure.toString()
            binding.windId.text = weather.wind.speed.toString()
            binding.weatherConditionId.text = weather.weather[0].main
            getUpdateTime()
        })
    }
    private fun getUpdateTime(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("EEEE LLLL yyyy HH:mm:ss aaa z")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        binding.updateTimeId.text = dateTime
    }

}