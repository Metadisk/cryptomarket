package com.metadisk.cryptomarket.data.repository.dataSourceImpl

import com.metadisk.cryptomarket.data.db.CoinDAO
import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.repository.dataSource.CoinLocalDataSource
import kotlinx.coroutines.flow.Flow

class CoinLocalDataSourceImpl(
    private val coinDAO: CoinDAO
) : CoinLocalDataSource {
    override suspend fun saveCoinsToDB(coin: Coin) {
        coinDAO.insert(coin)
    }

    override fun getSavedCoins(): Flow<List<Coin>> {
        return coinDAO.coinsAllList()
    }

    override suspend fun deleteCoinsFromDB() {
        coinDAO.deleteAll()
    }

    override fun getSavedSymbols(symbol: String): Flow<List<Coin>> {
        return coinDAO.coinsSearch(symbol)
    }

}