package com.metadisk.cryptomarket.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins_list")
data class Coin(
    @NonNull
    @PrimaryKey
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("market_cap_rank")
    val market_cap_rank: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("isFavourite")
    val isFavourite: Boolean = false,
    @SerializedName("image")
    val image: String,
    @SerializedName("current_price")
    val price: Double,
    @SerializedName("price_change_percentage_24h")
    val changePercent: Double
)