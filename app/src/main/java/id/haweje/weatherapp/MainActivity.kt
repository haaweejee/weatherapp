package id.haweje.weatherapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import id.haweje.weatherapp.core.utils.Resource
import id.haweje.weatherapp.core.utils.ViewModelFactory
import id.haweje.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

@DelicateCoroutinesApi
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
            if(binding.swipeRefreshLayout.isRefreshing){
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.swipeRefreshLayout.isRefreshing = false
                    showData()
                },500)
            }
        }
    }

    private fun showData(){
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
            viewModel.weather.observe(this@MainActivity, { weather ->
                if (weather != null){
                    when(weather){
                        is Resource.Loading ->
                            Snackbar.make(binding.mainLayout, "Tunggu Sebentar", Snackbar.LENGTH_SHORT).show()
                        is Resource.Success -> {
                            getUpdateTime()
                            val weatherTemp = weather.data?.temp
                            val celcius = (weatherTemp?.div(10))?.toInt()
                            binding.apply {
                                temperatureId.text = String.format(getString(R.string.temp), celcius.toString())
                                maxTemperatureId.text = String.format(getString(R.string.max_temp), weather.data?.tempMax?.toString())
                                minTemperatureId.text = String.format(getString(R.string.min_temp), weather.data?.tempMin?.toString())
                                cityNameId.text = weather.data?.name
                                humidityId.text = weather.data?.humidity.toString()
                                pressureId.text = weather.data?.pressure.toString()
                                windId.text = weather.data?.speed.toString()
                                weatherConditionId.text = weather.data?.weatherInfo
                            }
                            Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Error -> Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_SHORT).show()
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
