package id.haweje.weatherapp

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun getWeather() {
        val weatherViewModel = viewModel.weather
        val weatherData = viewModel.weather.value
        assertNotNull(weatherViewModel)
        if (weatherData != null) {
            assertNotNull(weatherData.name)
            assertEquals(weatherData.name, "Tangerang")
            assertNotNull(weatherData.country)
            assertEquals(weatherData.country, "ID")
            assertNotNull(weatherData.main.temp)
            assertNotNull(weatherData.main.tempMax)
            assertNotNull(weatherData.main.tempMin)
            assertNotNull(weatherData.main.humidity)
            assertNotNull(weatherData.main.pressure)
            assertNotNull(weatherData.weather[0].main)
            assertNotNull(weatherData.wind.speed)
        }

    }
}