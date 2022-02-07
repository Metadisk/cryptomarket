package com.metadisk.cryptomarket.presentation.di

import com.metadisk.cryptomarket.data.db.CoinDAO
import com.metadisk.cryptomarket.data.repository.dataSource.CoinLocalDataSource
import com.metadisk.cryptomarket.data.repository.dataSourceImpl.CoinLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(coinDAO: CoinDAO): CoinLocalDataSource {
      return CoinLocalDataSourceImpl(coinDAO)
    }

}













