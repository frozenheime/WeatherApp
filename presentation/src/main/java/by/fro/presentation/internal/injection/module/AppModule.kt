package by.fro.presentation.internal.injection.module

import android.content.Context
import by.fro.domain.Schedulers
import by.fro.presentation.internal.injection.DaggerApplication
import by.fro.presentation.internal.scheduler.AppSchedulers
import by.fro.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun providesContext(application: DaggerApplication): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideSchedulers(): Schedulers = AppSchedulers()

    @Provides
    @Singleton
    internal fun provideNavigator() = Navigator()

}