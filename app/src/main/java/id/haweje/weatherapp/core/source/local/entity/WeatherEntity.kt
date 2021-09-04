package id.haweje.weatherapp.core.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.haweje.weatherapp.core.source.remote.response.Main
import id.haweje.weatherapp.core.source.remote.response.WeatherItem
import id.haweje.weatherapp.core.source.remote.response.Wind

@Entity(tableName = "weather")
data class WeatherEntity(
    @ColumnInfo(name ="id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name="name")
    val name: String? = null,

    @ColumnInfo(name="country")
    val country: String? = null,

    @ColumnInfo(name="temp")
    val temp: Double? = null,

    @ColumnInfo(name="tempMin")
    val tempMin: Double? = null,

    @ColumnInfo(name="tempMax")
    val tempMax: Double? = null,

    @ColumnInfo(name="humidity")
    val humidity: Int? = null,

    @ColumnInfo(name = "pressure")
    val pressure: Int? = null,

    @ColumnInfo(name = "speed")
    val speed: Double? = null,

    @ColumnInfo(name = "main")
    val weatherInfo : String? = null,
)
