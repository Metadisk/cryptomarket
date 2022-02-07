package com.metadisk.cryptomarket.domain.repository

import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoin(vs_currency : String): Resource<List<Coin>>
    suspend fun saveNews(coin: Coin)
    suspend fun deleteNews()
    fun getSavedNews(): Flow<List<Coin>>
}