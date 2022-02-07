package com.metadisk.cryptomarket.presentation.di

import android.app.Application
import androidx.room.Room
import com.metadisk.cryptomarket.data.db.CoinDAO
import com.metadisk.cryptomarket.data.db.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideCoinsDatabase(app: Application): CoinDatabase {
        return Room.databaseBuilder(app, CoinDatabase::class.java, "coin_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(articleDatabase: CoinDatabase): CoinDAO {
        return articleDatabase.getArticleDAO()
    }


}