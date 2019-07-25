package by.fro.presentation.internal.injection.module.home


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.fro.domain.Schedulers
import by.fro.domain.gateway.InventoryGateway
import by.fro.domain.gateway.SystemGateway
import by.fro.domain.interactor.*
import by.fro.presentation.internal.injection.scope.HomeScope
import by.fro.presentation.weather.list.WeatherListFragment
import by.fro.presentation.weather.list.WeatherListViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun contributeWeatherListFragment(): WeatherListFragment

    @Module
    companion object {

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideGetCurrentCityUseCase(schedulers: Schedulers, systemGateway: SystemGateway): GetCurrentCityUseCase {
            return GetCurrentCityUseCase(schedulers, systemGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideGetCurrentWeatherByCityUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): GetCurrentWeatherByCityNameUseCase {
            return GetCurrentWeatherByCityNameUseCase(schedulers, inventoryGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideGetFavoriteCitiesUseCase(schedulers: Schedulers, systemGateway: SystemGateway): GetFavoriteCitiesUseCase {
            return GetFavoriteCitiesUseCase(schedulers, systemGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideGetCurrentWeatherInFavoriteCitiesUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): GetCurrentWeatherInFavoriteCitiesUseCase {
            return GetCurrentWeatherInFavoriteCitiesUseCase(schedulers, inventoryGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideAddFavoriteCityUseCase(schedulers: Schedulers, systemGateway: SystemGateway): AddFavoriteCityUseCase {
            return AddFavoriteCityUseCase(schedulers, systemGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideDeleteFavoriteCityUseCase(schedulers: Schedulers, systemGateway: SystemGateway): DeleteFavoriteCityUseCase {
            return DeleteFavoriteCityUseCase(schedulers, systemGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             getCurrentCityUseCase: GetCurrentCityUseCase,
                                             getCurrentWeatherInFavoriteCitiesUseCase: GetCurrentWeatherInFavoriteCitiesUseCase,
                                             getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase,
                                             addFavoriteCityUseCase: AddFavoriteCityUseCase,
                                             deleteFavoriteCityUseCase: DeleteFavoriteCityUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {

                        modelClass.isAssignableFrom(WeatherListViewModel::class.java) ->
                            WeatherListViewModel(context,
                                getCurrentWeatherInFavoriteCitiesUseCase,
                                getFavoriteCitiesUseCase,
                                addFavoriteCityUseCase,
                                deleteFavoriteCityUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}