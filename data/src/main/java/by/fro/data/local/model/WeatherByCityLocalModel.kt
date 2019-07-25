package by.fro.data.local.model


import androidx.room.Embedded
import androidx.room.Relation

data class WeatherByCityLocalModel(@Embedded val city: CityLocalModel) {

    @Relation(parentColumn = "id", entityColumn = "cityId", entity = WeatherLocalModel::class)
    lateinit var weatherList: List<WeatherLocalModel>
}