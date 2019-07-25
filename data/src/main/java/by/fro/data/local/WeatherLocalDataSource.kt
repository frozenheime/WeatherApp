package by.fro.data.local

import by.fro.data.local.dao.WeatherDao
import by.fro.data.local.model.WeatherLocalModel
import io.reactivex.Observable

class WeatherLocalDataSource(private val weatherDao: WeatherDao) {

    fun getWeatherByCityId(cityId: Int): Observable<WeatherLocalModel> = weatherDao.getWeatherByCityId(cityId).toObservable()

    fun getWeatherByCityName(cityName: String?): Observable<WeatherLocalModel> = weatherDao.getWeatherByCityName(cityName).toObservable()

    fun getWeatherInFavoriteCitiesList(): Observable<WeatherLocalModel> = weatherDao.getWeatherListInFavoriteCities().toObservable()

    fun insertAll(weather: List<WeatherLocalModel>) = weatherDao.insertAll(*weather.toTypedArray())

    fun insert(weather: WeatherLocalModel) = weatherDao.insert(weather)

    fun deleteWeatherById(id: Int) = weatherDao.deleteWeatherById(id)

    fun deleteWeatherByCityName(cityName: String) = weatherDao.deleteWeatherByCityName(cityName)
}