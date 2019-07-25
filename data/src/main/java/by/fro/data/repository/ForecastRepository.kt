package by.fro.data.repository

import by.fro.data.local.ForecastLocalDataSource
import by.fro.data.local.model.ForecastLocalModel
import by.fro.data.remote.ForecastRemoteDataSource
import by.fro.data.repository.mapper.ForecastMapper
import io.reactivex.Observable

class ForecastRepository(
    private val forecastLocalDataSource: ForecastLocalDataSource,
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastMapper: ForecastMapper) {

    fun getForecastByCityName(cityName: String?): Observable<ForecastLocalModel> {

        val local: Observable<ForecastLocalModel> = forecastLocalDataSource.getForecastByCityName(cityName)

        val remote: Observable<ForecastLocalModel> = forecastRemoteDataSource.getForecastByCityName(cityName)
            .map { forecastMapper.toLocal(it) }
            .doOnNext { forecastLocalDataSource.insert(it.city, it.weatherList) }

        return Observable.concat(local, remote)
            .firstElement()
            .toObservable()
    }

    fun getFavoriteCitiesForecasts(): Observable<List<ForecastLocalModel>> {
        return forecastLocalDataSource.getFavoriteCitiesForecastList()
            .firstElement()
            .toObservable()
    }
}