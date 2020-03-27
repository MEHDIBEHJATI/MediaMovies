package ir.airport.myapplication.Retrofit

import dagger.Component

@Component
interface FactoryComponent {
    fun provideVMFactory():VMFactoryProvider
}