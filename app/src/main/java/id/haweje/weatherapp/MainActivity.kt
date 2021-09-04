package id.haweje.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.haweje.weatherapp.core.utils.Status
import id.haweje.weatherapp.core.utils.ViewModelFactory
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
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getWeatherData().observe(this, {
            if (it != null){
                when(it.status){
                    Status.LOADING -> {
                        binding.mainLayout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.mainLayout.visibility = View.VISIBLE
                        val weatherTemp = it.data?.temp
                        val celcius = (weatherTemp?.div(10))?.toInt()
                        binding.temperatureId.text = String.format(getString(R.string.temp), celcius.toString())
                        binding.maxTemperatureId.text = String.format(getString(R.string.max_temp), it.data?.tempMax?.toString())
                        binding.minTemperatureId.text = String.format(getString(R.string.min_temp), it.data?.tempMin?.toString())
                        binding.cityNameId.text = it.data?.name
                        binding.humidityId.text = it.data?.humidity.toString()
                        binding.pressureId.text = it.data?.pressure.toString()
                        binding.windId.text = it.data?.speed.toString()
                        binding.weatherConditionId.text = it.data?.weatherInfo
                        getUpdateTime()
                    }
                    Status.ERROR -> {
                        binding.mainLayout.visibility = View.GONE
                        Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }


    private fun getUpdateTime(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("EEEE LLLL yyyy HH:mm:ss aaa z")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        binding.updateTimeId.text = dateTime
    }

}
