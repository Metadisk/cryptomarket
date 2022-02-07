package com.metadisk.cryptomarket.data.repository.dataSourceImpl

import com.metadisk.cryptomarket.data.api.CoinAPIService
import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.repository.dataSource.CoinRemoteDataSource
import com.metadisk.cryptomarket.data.util.Resource
import retrofit2.Response

class CoinRemoteDataSourceImpl(
    private val  coinAPIService: CoinAPIService): CoinRemoteDataSource {

    override suspend fun getCoin(vs_currency: String): Response<List<Coin>> {
        return coinAPIService.coinsList(vs_currency)
    }
}