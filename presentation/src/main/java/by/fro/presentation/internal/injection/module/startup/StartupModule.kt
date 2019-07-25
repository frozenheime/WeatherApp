package by.fro.presentation.internal.injection.module.startup


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.fro.domain.Schedulers
import by.fro.domain.gateway.SystemGateway
import by.fro.domain.interactor.GetCurrentCityUseCase
import by.fro.domain.interactor.GetFavoriteCitiesUseCase
import by.fro.presentation.internal.injection.scope.StartupScope
import by.fro.presentation.startup.StartupViewModel
import dagger.Module
import dagger.Provides

@Module
internal class StartupModule {

    @StartupScope
    @Provides
    internal fun provideGetCurrentCityUseCase(schedulers: Schedulers, systemGateway: SystemGateway): GetCurrentCityUseCase{
        return GetCurrentCityUseCase(schedulers, systemGateway)
    }

    @StartupScope
    @Provides
    internal fun provideGetFavoriteCitiesUseCase(schedulers: Schedulers, systemGateway: SystemGateway): GetFavoriteCitiesUseCase{
        return GetFavoriteCitiesUseCase(schedulers, systemGateway)
    }

    @Provides
    internal fun provideViewModelFactory(context: Context,
                                         getCurrentCityUseCase: GetCurrentCityUseCase,
                                         getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return when {
                    modelClass.isAssignableFrom(StartupViewModel::class.java) ->
                        StartupViewModel(context, getCurrentCityUseCase, getFavoriteCitiesUseCase) as T

                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}