package com.metadisk.cryptomarket.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinAPIServiceTest {

    private lateinit var service: CoinAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinAPIService::class.java)
    }


    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }


    @Test
    fun getTopHeadlines_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("coinsresponse.json")
            val responseBody = service.coinsList("usd").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v3/coins/markets?vs_currency=usd")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("coinsresponse.json")
            val responseBody = service.coinsList("usd").body()
            val coinsList = responseBody!!
            assertThat(coinsList.size).isEqualTo(100)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("coinsresponse.json")
            val responseBody = service.coinsList("usd").body()
            val coinsList = responseBody
            val coin = coinsList?.get(0)
            assertThat(coin!!.name).isEqualTo("Bitcoin")
            assertThat(coin.market_cap_rank).isEqualTo(1)
            assertThat(coin.symbol).isEqualTo("btc")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}