package com.metadisk.cryptomarket.data.db

import androidx.room.*
import com.metadisk.cryptomarket.data.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDAO {

    //Returns all stocks
    @Query("Select * from coins_list")
    fun coinsAllList(): Flow<List<Coin>>

    //Returns all stocks
    @Query("Select * from coins_list where symbol = :symbol")
    fun coinsSearch(symbol: String): Flow<List<Coin>>

    //Inserts data. If row already exists, replace the row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockList: Coin)

    //Delete all rows
    @Query("Delete from coins_list")
    suspend fun deleteAll()

}