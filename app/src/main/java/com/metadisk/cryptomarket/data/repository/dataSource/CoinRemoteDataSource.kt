package com.metadisk.cryptomarket.data.repository.dataSource

import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.util.Resource
import retrofit2.Response

interface CoinRemoteDataSource {
    suspend fun getCoin(vs_currency : String, per_page: Int, page: Int): Response<List<Coin>>
}