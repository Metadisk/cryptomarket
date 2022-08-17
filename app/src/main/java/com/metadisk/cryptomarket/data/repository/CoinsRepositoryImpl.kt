package com.metadisk.cryptomarket.data.repository

import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.repository.dataSource.CoinLocalDataSource
import com.metadisk.cryptomarket.data.repository.dataSource.CoinRemoteDataSource
import com.metadisk.cryptomarket.data.util.Resource
import com.metadisk.cryptomarket.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CoinsRepositoryImpl (
    private val coinLocalDataSource: CoinLocalDataSource,
    private val coinRemoteDataSource: CoinRemoteDataSource)
    :CoinRepository{


    override suspend fun getCoin(vs_currency: String, per_page: Int, page: Int): Resource<List<Coin>> {
        return responseToResource(coinRemoteDataSource.getCoin(vs_currency, per_page, page))
    }

    private fun responseToResource(response: Response<List<Coin>>):Resource<List<Coin>>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(coin: Coin) {
        coinLocalDataSource.saveCoinsToDB(coin)
    }

    override suspend fun deleteNews() {
        coinLocalDataSource.deleteCoinsFromDB()
    }

    override fun getSavedNews(): Flow<List<Coin>> {
        return coinLocalDataSource.getSavedCoins()
    }


}