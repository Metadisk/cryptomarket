package com.metadisk.cryptomarket.presentation.di

import com.metadisk.cryptomarket.domain.repository.CoinRepository
import com.metadisk.cryptomarket.domain.usecase.GetCoinsUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetCoinUseCase(
        newsRepository: CoinRepository
    ): GetCoinsUsecase {
        return GetCoinsUsecase(newsRepository)
    }

}


















