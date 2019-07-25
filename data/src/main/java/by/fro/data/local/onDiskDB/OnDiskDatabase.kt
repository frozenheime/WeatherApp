package by.fro.data.local.onDiskDB


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.fro.data.local.dao.CityDao
import by.fro.data.local.dao.ForecastDao
import by.fro.data.local.dao.WeatherDao
import by.fro.data.local.model.CityLocalModel
import by.fro.data.local.model.ForecastWeatherLocalModel
import by.fro.data.local.model.WeatherLocalModel
import by.fro.data.local.util.Converters

@Database(entities = [CityLocalModel::class, WeatherLocalModel::class, ForecastWeatherLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class OnDiskDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun forecastDao(): ForecastDao

    abstract fun weatherDao(): WeatherDao

    companion object {
        fun newInstance(context: Context): OnDiskDatabase {
            return Room.databaseBuilder(context, OnDiskDatabase::class.java, "weatherApp.db").build()
        }
    }
}