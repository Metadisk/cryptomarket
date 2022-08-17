package com.metadisk.cryptomarket.data.api

import com.metadisk.cryptomarket.data.model.Coin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinAPIService {

    @GET("v3/coins/markets")
    suspend fun coinsList(
        @Query("vs_currency") targetCurrency: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<List<Coin>>
    
}