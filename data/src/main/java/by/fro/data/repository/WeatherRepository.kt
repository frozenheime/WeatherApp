package by.fro.data.repository

import by.fro.data.local.WeatherLocalDataSource
import by.fro.data.local.model.WeatherLocalModel
import by.fro.data.remote.WeatherRemoteDataSource
import by.fro.data.repository.mapper.WeatherMapper
import io.reactivex.Observable

class WeatherRepository(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherMapper: WeatherMapper
) {

    fun getWeatherByCityName(cityName: String?): Observable<WeatherLocalModel> {

        val local = weatherLocalDataSource.getWeatherByCityName(cityName)

        val remote = weatherRemoteDataSource.getWeather(cityName)
            .map { weatherMapper.toLocal(it) }
            .doOnNext { weatherLocalDataSource.insert(it) }

        return Observable.concat(remote, local)
            .firstElement()
            .toObservable()
    }

    fun getCurrentWeatherInFavoriteCitiesList(cityNames: ArrayList<String>): Observable<WeatherLocalModel> {

        val remote = weatherRemoteDataSource.getWeather(cityNames)
            .map { weatherMapper.toLocal(it) }
            .doOnNext { weatherLocalDataSource.insert(it) }

        val local = weatherLocalDataSource.getWeatherInFavoriteCitiesList()

        return Observable.concat(local, remote)
    }
}
