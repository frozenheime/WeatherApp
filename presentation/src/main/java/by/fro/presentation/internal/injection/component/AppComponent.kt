package by.fro.presentation.internal.injection.component

import by.fro.presentation.internal.injection.DaggerApplication
import by.fro.presentation.internal.injection.module.ActivitiesModule
import by.fro.presentation.internal.injection.module.AppModule
import by.fro.presentation.internal.injection.module.DataModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivitiesModule::class,
    AppModule::class,
    DataModule::class])
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}