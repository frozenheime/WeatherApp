package by.fro.data.local.dao


import androidx.room.*
import by.fro.data.local.model.CityLocalModel
import by.fro.data.local.model.ForecastLocalModel
import by.fro.data.local.model.ForecastWeatherLocalModel
import io.reactivex.Maybe

@Dao
interface ForecastDao {

    @Transaction
    @Query("SELECT * FROM ForecastWeather WHERE id = :cityId")
    fun getForecastByCityId(cityId: Int): Maybe<ForecastLocalModel>

    @Query("SELECT * FROM ForecastWeather WHERE cityName = :cityName")
    fun getForecastByCityName(cityName: String?): Maybe<ForecastLocalModel>

    @Query("SELECT * FROM ForecastWeather WHERE id = (SELECT id FROM City WHERE favorite = 'true')")
    fun getFavoriteCitiesForecastsList(): Maybe<List<ForecastLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg city: ForecastWeatherLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityLocalModel)

    @Query("DELETE FROM ForecastWeather WHERE id = :id")
    fun deleteForecastById(id: Int)

    @Query("DELETE FROM ForecastWeather WHERE cityName = :cityName")
    fun deleteForecastByCityName(cityName: String)
}