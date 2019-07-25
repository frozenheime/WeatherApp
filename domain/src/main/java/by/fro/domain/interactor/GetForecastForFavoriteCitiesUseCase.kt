package by.fro.domain.interactor

import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.Forecast
import by.fro.domain.gateway.InventoryGateway
import io.reactivex.Observable

class GetForecastForFavoriteCitiesUseCase(schedulers: Schedulers, private val inventoryGateway: InventoryGateway): UseCase<Void, List<Forecast>>(schedulers) {

    override fun buildObservable(params: Void?): Observable<List<Forecast>> {
        return inventoryGateway.getForecastInFavoriteCities()
    }
}