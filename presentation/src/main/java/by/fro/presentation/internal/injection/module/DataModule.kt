package by.fro.presentation.internal.injection.module

import android.app.Activity
import android.content.Context
import by.fro.data.BuildConfig
import by.fro.data.gateway.InventoryGatewayImpl
import by.fro.data.gateway.SystemGatewayImpl
import by.fro.data.local.CityLocalDataSource
import by.fro.data.local.ForecastLocalDataSource
import by.fro.data.local.WeatherLocalDataSource
import by.fro.data.local.dao.CityDao
import by.fro.data.local.dao.ForecastDao
import by.fro.data.local.dao.WeatherDao
import by.fro.data.local.onDiskDB.OnDiskDatabase
import by.fro.data.remote.CityRemoteDataSource
import by.fro.data.remote.ForecastRemoteDataSource
import by.fro.data.remote.WeatherRemoteDataSource
import by.fro.data.remote.api.WeatherApi
import by.fro.data.remote.api.WeatherService
import by.fro.data.remote.location.LocationAccessObject
import by.fro.data.repository.CityRepository
import by.fro.data.repository.ForecastRepository
import by.fro.data.repository.WeatherRepository
import by.fro.data.repository.mapper.CityMapper
import by.fro.data.repository.mapper.ForecastMapper
import by.fro.data.repository.mapper.WeatherMapper
import by.fro.domain.gateway.InventoryGateway
import by.fro.domain.gateway.SystemGateway
import by.fro.presentation.internal.injection.scope.StartupScope
import by.fro.presentation.startup.StartupActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @Singleton
    internal fun provideTheatreService(): WeatherService = WeatherApi(BuildConfig.API_URL)

    @Provides
    @Singleton
    internal fun provideLocationAccessObject(context: Context): LocationAccessObject {
        return LocationAccessObject(context)
    }

    @Provides
    @Singleton
    internal fun provideCityRemoteDataSource(locationAccessObject: LocationAccessObject): CityRemoteDataSource {
        return CityRemoteDataSource(locationAccessObject)
    }

    @Provides
    @Singleton
    internal fun provideForecastRemoteDataSource(weatherService: WeatherService): ForecastRemoteDataSource {
        return ForecastRemoteDataSource(weatherService)
    }

    @Provides
    @Singleton
    internal fun provideWeatherRemoteDataSource(weatherService: WeatherService): WeatherRemoteDataSource {
        return WeatherRemoteDataSource(weatherService)
    }

    @Provides
    @Singleton
    internal fun provideSystemDatabase(context: Context): OnDiskDatabase {
        return OnDiskDatabase.newInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideCityTypeDao(onDiskDatabase: OnDiskDatabase): CityDao = onDiskDatabase.cityDao()

    @Provides
    @Singleton
    internal fun provideForecastDao(onDiskDatabase: OnDiskDatabase): ForecastDao = onDiskDatabase.forecastDao()

    @Provides
    @Singleton
    internal fun provideWeatherDao(onDiskDatabase: OnDiskDatabase): WeatherDao = onDiskDatabase.weatherDao()

    @Provides
    @Singleton
    internal fun provideCityDiskDataSource(cityDao: CityDao): CityLocalDataSource {
        return CityLocalDataSource(cityDao)
    }

    @Provides
    @Singleton
    internal fun provideForecastDiskDataSource(forecastDao: ForecastDao): ForecastLocalDataSource {
        return ForecastLocalDataSource(forecastDao)
    }

    @Provides
    @Singleton
    internal fun provideWeatherDiskDataSource(weatherDao: WeatherDao): WeatherLocalDataSource {
        return WeatherLocalDataSource(weatherDao)
    }

    @Provides
    @Singleton
    internal fun provideCityRepository(cityLocalDataSource: CityLocalDataSource,
                                            cityRemoteDataSource: CityRemoteDataSource): CityRepository {
        return CityRepository(cityLocalDataSource, cityRemoteDataSource, CityMapper())
    }

    @Provides
    @Singleton
    internal fun provideForecastRepository(forecastLocalDataSource: ForecastLocalDataSource,
                                        forecastRemoteDataSource: ForecastRemoteDataSource): ForecastRepository {
        return ForecastRepository(forecastLocalDataSource, forecastRemoteDataSource, ForecastMapper())
    }

    @Provides
    @Singleton
    internal fun provideWeatherRepository(weatherLocalDataSource: WeatherLocalDataSource,
                                          weatherRemoteDataSource: WeatherRemoteDataSource): WeatherRepository {
        return WeatherRepository(weatherLocalDataSource, weatherRemoteDataSource, WeatherMapper())
    }


    @Provides
    @Singleton
    internal fun provideSystemRepository(cityRepository: CityRepository): SystemGateway {
        return SystemGatewayImpl(cityRepository)
    }

    @Provides
    @Singleton
    internal fun provideInventoryRepository(forecastRepository: ForecastRepository,
                                            weatherRepository: WeatherRepository): InventoryGateway {
        return InventoryGatewayImpl(forecastRepository, weatherRepository)
    }
}