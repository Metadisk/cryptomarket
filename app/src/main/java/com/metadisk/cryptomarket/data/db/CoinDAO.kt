package com.metadisk.cryptomarket.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.metadisk.cryptomarket.data.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDAO {

    //Returns all stocks
    @Query("Select * from coins_list")
    fun coinsAllList(): Flow<List<Coin>>

    //Returns stocks based on symbol
    @Query("Select * from coins_list where symbol = :symbol")
    fun projectFromSymbol(symbol: String): Flow<List<Coin>>

/*
    //Returns stocks livedata based on symbol
    @Query("Select * from coins_list where symbol = :symbol")
    fun projectLiveDataFromSymbol(symbol: String): Flow<Coin>
*/

/*    //Retruns all favourite stocks
    @Query("Select * from coins_list where isFavourite = 1")
    fun favouriteCoins(): Flow<List<Coin>>*/

/*    //Returns all favourite symbols
    @Query("Select symbol from coins_list where isFavourite = 1")
    fun favouriteSymbols(): List<String>*/

    //Inserts data. If row already exists, replace the row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockList: Coin)

/*    //Update the row
    @Update
    fun updateCoinsListEntity(data: Coin): Int*/

    //Delete all rows
    @Query("Delete from coins_list")
    suspend fun deleteAll()

}