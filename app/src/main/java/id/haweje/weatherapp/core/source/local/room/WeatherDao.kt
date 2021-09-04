package id.haweje.weatherapp.core.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeatherData() : LiveData<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(weather: WeatherEntity)
}