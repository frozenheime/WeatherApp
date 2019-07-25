package by.fro.data.local.model


import androidx.room.Embedded
import androidx.room.Relation

data class ForecastLocalModel(@Embedded val city: CityLocalModel) {

    @Relation(parentColumn = "id", entityColumn = "id", entity = ForecastWeatherLocalModel::class)
    lateinit var weatherList: List<ForecastWeatherLocalModel>
}