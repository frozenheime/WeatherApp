package by.fro.data.local.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ForecastWeather")
data class ForecastWeatherLocalModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val cityId: Int,
    val cityName: String?,
    val description: String?,
    val dtTxt: String?,
    val windSpeed: Double?,
    val windDeg: Double?,
    val clouds: Int?,
    val rain1h: Double?,
    val rain3h: Double?,
    val snow1h: Double?,
    val snow3h: Double?,
    val temperature: Double?,
    val temperatureMin: Double?,
    val temperatureMax: Double?,
    val pressure: Double?,
    val humidity: Double?
)