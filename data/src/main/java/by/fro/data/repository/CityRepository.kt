package by.fro.data.repository

import by.fro.data.local.CityLocalDataSource
import by.fro.data.local.model.CityLocalModel
import by.fro.data.remote.CityRemoteDataSource
import by.fro.data.repository.mapper.CityMapper
import by.fro.domain.entity.City
import io.reactivex.Observable
import io.reactivex.Observer

class CityRepository(
    private val cityLocalDataSource: CityLocalDataSource,
    private val cityRemoteDataSource: CityRemoteDataSource,
    private val cityMapper: CityMapper) {

    fun getCurrentCity(refresh: Boolean? = false): Observable<CityLocalModel> {

        val local = cityLocalDataSource.getCurrentCity()

        val remote = cityRemoteDataSource.getCurrentCity()
            .map { cityMapper.toLocal(it) }
            .doOnNext { cityLocalDataSource.insert(it).subscribe{
                println("ROW NUMBER: $it")
            }
                println("INSERTED INTO LOCAL: $it")}

        return Observable.just(false)
            .flatMap {
                Observable.concat(local, remote)
            }
    }

    fun getFavoriteCities(): Observable<List<CityLocalModel>> {
        return cityLocalDataSource.getFavoriteCities()
    }

    fun addFavoriteCity(city: City?): Observable<Long> {
        return cityLocalDataSource.insert(cityMapper.toLocal(city!!))
    }

    fun deleteFavoriteCity(city: City?): Observable<Int> {
        return cityLocalDataSource.deleteFavorite(city!!)
    }
}