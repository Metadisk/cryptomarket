package com.metadisk.cryptomarket.domain.repository

import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoin(vs_currency : String, per_page: Int, page: Int): Resource<List<Coin>>
    suspend fun saveNews(coin: Coin)
    fun getSavedNews(): Flow<List<Coin>>
    suspend fun deleteNews()
}