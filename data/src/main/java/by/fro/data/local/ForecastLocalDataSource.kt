package by.fro.data.local

import by.fro.data.local.dao.ForecastDao
import by.fro.data.local.model.CityLocalModel
import by.fro.data.local.model.ForecastLocalModel
import by.fro.data.local.model.ForecastWeatherLocalModel
import io.reactivex.Observable

class ForecastLocalDataSource(private val forecastDao: ForecastDao) {

    fun getForecastByCityId(cityId: Int): io.reactivex.Observable<ForecastLocalModel> = forecastDao.getForecastByCityId(cityId).toObservable()

    fun getForecastByCityName(cityName: String?): Observable<ForecastLocalModel> = forecastDao.getForecastByCityName(cityName).toObservable()

    fun insertAll(forecast: List<ForecastWeatherLocalModel>) = forecastDao.insertAll(*forecast.toTypedArray())

    fun insert(city: CityLocalModel, weatherForecasts: List<ForecastWeatherLocalModel>) {
        forecastDao.insert(city)
        forecastDao.insertAll(*weatherForecasts.toTypedArray())
    }

    fun deleteForecastById(id: Int) = forecastDao.deleteForecastById(id)

    fun deleteForecastByCityName(cityName: String) = forecastDao.deleteForecastByCityName(cityName)

    fun getFavoriteCitiesForecastList() = forecastDao.getFavoriteCitiesForecastsList().toObservable()
}