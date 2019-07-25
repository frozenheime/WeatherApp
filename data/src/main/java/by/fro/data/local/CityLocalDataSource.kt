package by.fro.data.local

import by.fro.data.local.dao.CityDao
import by.fro.data.local.model.CityLocalModel
import by.fro.domain.entity.City
import io.reactivex.Observable

class CityLocalDataSource(private val cityDao: CityDao) {

    fun getCurrentCity(): Observable<CityLocalModel> = cityDao.getCurrentCity().toObservable()

    fun getFavoriteCities(): Observable<List<CityLocalModel>> = cityDao.getFavoriteCities().toObservable()

    fun insertAll(cities: List<CityLocalModel>) = cityDao.insertAll(*cities.toTypedArray())

    fun insert(city: CityLocalModel): Observable<Long> = cityDao.insert(city).toObservable()

    fun deleteFavorite(city: City) = cityDao.deleteFavoriteByName(city.name!!).toObservable()
}