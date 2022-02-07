package com.anushka.newsapiclient.presentation.di


import com.metadisk.cryptomarket.data.repository.CoinsRepositoryImpl
import com.metadisk.cryptomarket.data.repository.dataSource.CoinLocalDataSource
import com.metadisk.cryptomarket.data.repository.dataSource.CoinRemoteDataSource
import com.metadisk.cryptomarket.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCoinsRepository(
        coinRemoteDataSource: CoinRemoteDataSource,
        coinLocalDataSource: CoinLocalDataSource
    ): CoinRepository {
        return CoinsRepositoryImpl(
            coinLocalDataSource,
            coinRemoteDataSource
        )
    }

}














