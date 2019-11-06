package com.example.stackoverflow;

import android.app.Application
import com.example.stackoverflow.dagger.AppComponent
import com.example.stackoverflow.dagger.AppModule
import com.example.stackoverflow.dagger.DaggerAppComponent


class BaseApp : Application() {
    lateinit var baseComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        baseComponent = initDagger(this)
    }

    private fun initDagger(app: BaseApp) =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}