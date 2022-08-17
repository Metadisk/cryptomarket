package com.metadisk.cryptomarket.presentation.di

import android.app.Application
import com.metadisk.cryptomarket.domain.usecase.GetCoinsUsecase
import com.metadisk.cryptomarket.presentation.viewmodel.CoinViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
  fun provideCoinViewModelFactory(
     application: Application,
     getCoinsUsecase: GetCoinsUsecase,
  ):CoinViewModelFactory{
      return CoinViewModelFactory(
          application,
          getCoinsUsecase,
      )
  }
}








