package by.fro.data.remote

import by.fro.data.remote.location.LocationAccessObject
import by.fro.data.remote.model.CityRemoteModel
import io.reactivex.Observable

class CityRemoteDataSource(private val locationAccessObject: LocationAccessObject) {

    fun getCurrentCity(): Observable<CityRemoteModel> = locationAccessObject.getCity()
}