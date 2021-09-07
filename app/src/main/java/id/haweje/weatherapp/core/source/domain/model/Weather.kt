package id.haweje.weatherapp.core.source.domain.model

data class Weather(
    var id: Int = 0,
    val name: String? = null,
    val country: String? = null,
    val temp: Double? = null,
    val tempMin: Double? = null,
    val tempMax: Double? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val speed: Double? = null,
    val weatherInfo: String? = null,
)
