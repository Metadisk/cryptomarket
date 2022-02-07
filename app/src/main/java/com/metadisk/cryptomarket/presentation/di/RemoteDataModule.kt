package com.anushka.newsapiclient.presentation.di


import com.metadisk.cryptomarket.data.api.CoinAPIService
import com.metadisk.cryptomarket.data.repository.dataSource.CoinRemoteDataSource
import com.metadisk.cryptomarket.data.repository.dataSourceImpl.CoinRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideCoinsRemoteDataSource(
        newsAPIService: CoinAPIService
    ): CoinRemoteDataSource {
       return CoinRemoteDataSourceImpl(newsAPIService)
    }

}












