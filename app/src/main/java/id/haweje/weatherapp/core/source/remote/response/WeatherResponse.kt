package id.haweje.weatherapp.core.source.remote.response

import androidx.annotation.NonNull
import com.squareup.moshi.Json

data class WeatherResponse(

	@Json(name="name")
	val name: String,

	@Json(name="country")
	val country: String,

	@Json(name="weather")
	val weather: List<WeatherItem>,

	@Json(name="main")
	val main: Main,


	@Json(name="wind")
	val wind: Wind
)

data class Main(

	@Json(name="temp")
	val temp: Double,

	@Json(name="temp_min")
	val tempMin: Double,

	@Json(name="temp_max")
	val tempMax: Double,

	@Json(name="humidity")
	val humidity: Int,

	@Json(name="pressure")
	val pressure: Int

)

data class WeatherItem(
	@Json(name="main")
	val main: String,
)

data class Wind(

	@Json(name="speed")
	val speed: Double
)
