package by.fro.presentation.internal.injection.module

import by.fro.presentation.home.HomeActivity
import by.fro.presentation.internal.injection.module.home.HomeModule
import by.fro.presentation.internal.injection.module.startup.StartupModule
import by.fro.presentation.internal.injection.module.weather.WeatherModule
import by.fro.presentation.internal.injection.scope.HomeScope
import by.fro.presentation.internal.injection.scope.StartupScope
import by.fro.presentation.internal.injection.scope.WeatherScope
import by.fro.presentation.startup.StartupActivity
import by.fro.presentation.weather.detail.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivitiesModule {

    @StartupScope
    @ContributesAndroidInjector(modules = [StartupModule::class])
    internal abstract fun contributeStartupActivity(): StartupActivity

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun contributeHomeActivity(): HomeActivity

    @WeatherScope
    @ContributesAndroidInjector(modules = [WeatherModule::class])
    internal abstract fun contributeWeatherActivity(): WeatherActivity
}