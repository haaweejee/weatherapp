package id.haweje.weatherapp.core.source.remote.response

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

	@Json(name="humidity")
	val humidity: Int,

	@Json(name="pressure")
	val pressure: Int,

	@Json(name="feels_like")
	val feelsLike: Double,

	@Json(name="temp_max")
	val tempMax: Double
)

data class WeatherItem(

	@Json(name="icon")
	val icon: String,

	@Json(name="description")
	val description: String,

	@Json(name="main")
	val main: String,

	@Json(name="id")
	val id: Int
)

data class Wind(

	@Json(name="deg")
	val deg: Int,

	@Json(name="speed")
	val speed: Double
)
