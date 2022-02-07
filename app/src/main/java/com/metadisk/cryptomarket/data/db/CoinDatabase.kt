package com.metadisk.cryptomarket.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metadisk.cryptomarket.data.model.Coin

@Database(
    entities = [Coin::class],
    version = 1,
    exportSchema = false
)

abstract class CoinDatabase : RoomDatabase(){
    abstract fun getArticleDAO():CoinDAO
}