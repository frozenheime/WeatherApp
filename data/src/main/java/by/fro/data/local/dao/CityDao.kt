package by.fro.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.fro.data.local.model.CityLocalModel
import io.reactivex.Maybe

@Dao
interface CityDao {

    @Query("SELECT * FROM City WHERE Current = 1")
    fun getCurrentCity(): Maybe<CityLocalModel>

    @Query("SELECT * FROM City")
    fun getFavoriteCities(): Maybe<List<CityLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg cities: CityLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityLocalModel): Maybe<Long>

    @Query("DELETE FROM City WHERE name = :cityName")
    fun deleteFavoriteByName(cityName: String): Maybe<Int>
}