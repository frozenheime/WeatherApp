package by.fro.presentation.internal.injection.module

import by.fro.domain.Schedulers
import by.fro.domain.gateway.InventoryGateway
import by.fro.domain.gateway.SystemGateway
import by.fro.domain.interactor.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DomainModule {

    @Provides
    @Singleton
    internal fun provideGetCurrentCityUseCase(schedulers: Schedulers, systemGateway: SystemGateway): GetCurrentCityUseCase {
        return GetCurrentCityUseCase(schedulers, systemGateway)
    }

    @Provides
    @Singleton
    internal fun provideCurrentWeatherByCityUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): GetCurrentWeatherByCityNameUseCase{
        return GetCurrentWeatherByCityNameUseCase(schedulers, inventoryGateway)
    }

    @Provides
    @Singleton
    internal fun provideCurrentWeatherInFavoriteCitiesUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): GetCurrentWeatherInFavoriteCitiesUseCase {
        return GetCurrentWeatherInFavoriteCitiesUseCase(schedulers, inventoryGateway)
    }

    @Provides
    @Singleton
    internal fun provideFavoriteCitiesUseCase(schedulers: Schedulers, systemGateway: SystemGateway): GetFavoriteCitiesUseCase {
        return GetFavoriteCitiesUseCase(schedulers, systemGateway)
    }

    @Provides
    @Singleton
    internal fun provideForecastForFavoriteCitiesUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): GetForecastForFavoriteCitiesUseCase {
        return GetForecastForFavoriteCitiesUseCase(schedulers, inventoryGateway)
    }

    @Provides
    @Singleton
    internal fun provideForecastUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): GetForecastUseCase {
        return GetForecastUseCase(schedulers, inventoryGateway)
    }
}