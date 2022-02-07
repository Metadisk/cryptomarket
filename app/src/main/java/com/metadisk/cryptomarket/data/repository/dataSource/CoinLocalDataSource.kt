package com.metadisk.cryptomarket.data.repository.dataSource

import com.metadisk.cryptomarket.data.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinLocalDataSource {

    suspend fun saveCoinsToDB(coin: Coin)
    fun getSavedCoins(): Flow<List<Coin>>
    suspend fun deleteCoinsFromDB()
    fun getSavedSymbols(symbol: String): Flow<List<Coin>>
    //fun getSavedFavorites(): Flow<List<Coin>>
    //fun updatedCoins(): List<Coin>
    //fun insertCoins(): List<Coin>
}