package by.fro.domain.interactor

import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.City
import by.fro.domain.entity.Forecast
import by.fro.domain.gateway.InventoryGateway
import io.reactivex.Observable

class GetForecastUseCase(schedulers: Schedulers, private val inventoryGateway: InventoryGateway): UseCase<City, Forecast>(schedulers) {

    override fun buildObservable(params: City?): Observable<Forecast> {
        return inventoryGateway.getForecastByCity(params)
    }
}