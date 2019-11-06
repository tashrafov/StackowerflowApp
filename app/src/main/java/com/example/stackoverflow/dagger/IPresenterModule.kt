package com.example.stackoverflow.dagger

import com.example.stackoverflow.ui.details.DetailPresenter
import com.example.stackoverflow.ui.details.IDetailPresenter
import com.example.stackoverflow.ui.main.IMainPresenter
import com.example.stackoverflow.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class IPresenterModule {
    @Provides
    @Singleton
    fun provideMainPresenter(): IMainPresenter.Presenter = MainPresenter()

    @Provides
    @Singleton
    fun provideDetailPresenter(): IDetailPresenter.Presenter = DetailPresenter()
}