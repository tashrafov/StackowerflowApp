package com.example.stackoverflow.dagger

import com.example.stackoverflow.ui.details.DetailsActivity
import com.example.stackoverflow.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, IPresenterModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: DetailsActivity)
}