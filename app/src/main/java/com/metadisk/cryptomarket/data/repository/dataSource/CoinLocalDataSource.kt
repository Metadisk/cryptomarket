package com.metadisk.cryptomarket.data.repository.dataSource

import com.metadisk.cryptomarket.data.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinLocalDataSource {

    suspend fun saveCoinsToDB(coin: Coin)
    fun getSavedCoins(): Flow<List<Coin>>
    suspend fun deleteCoinsFromDB()
    fun getSavedSymbols(symbol: String): Flow<List<Coin>>
}