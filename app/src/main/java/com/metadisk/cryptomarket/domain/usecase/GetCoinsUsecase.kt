package com.metadisk.cryptomarket.domain.usecase

import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.util.Resource
import com.metadisk.cryptomarket.domain.repository.CoinRepository

class GetCoinsUsecase (private val coinRepository: CoinRepository) {

    suspend fun execute(vs_currency : String): Resource<List<Coin>> {
        return coinRepository.getCoin(vs_currency)
    }
}