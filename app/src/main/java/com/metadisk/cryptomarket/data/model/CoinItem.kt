package com.metadisk.cryptomarket.data.model

import com.google.gson.annotations.SerializedName

data class CoinItem(
    val id: String,
    val image: String,
    val name: String,
    @SerializedName("price_change_percentage_24h")
    val price_change_percentage_24h: Double,
    val symbol: String,
    @SerializedName("current_price")
    val price: Double?
)