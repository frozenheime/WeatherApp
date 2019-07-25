package by.fro.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.fro.data.local.model.ForecastLocalModel
import by.fro.data.local.model.WeatherLocalModel
import io.reactivex.Maybe

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather WHERE cityId = :cityId")
    fun getWeatherByCityId(cityId: Int): Maybe<WeatherLocalModel>

    @Query("SELECT * FROM Weather WHERE cityName = :cityName")
    fun getWeatherByCityName(cityName: String?): Maybe<WeatherLocalModel>

    @Query("SELECT * FROM Weather WHERE cityId = (SELECT id FROM City WHERE favorite = 1)")
    fun getWeatherListInFavoriteCities(): Maybe<WeatherLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg Weather: WeatherLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: WeatherLocalModel)

    @Query("DELETE FROM Weather WHERE id = :id")
    fun deleteWeatherById(id: Int)

    @Query("DELETE FROM Weather WHERE cityName = :cityName")
    fun deleteWeatherByCityName(cityName: String)

}