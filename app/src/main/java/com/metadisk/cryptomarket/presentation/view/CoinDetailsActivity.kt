package com.metadisk.cryptomarket.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.metadisk.cryptomarket.databinding.CoindetailsBinding
import dagger.hilt.android.AndroidEntryPoint

class CoinDetailsActivity : AppCompatActivity() {

    var id: String? = null
    var name: String? = null
    var image: String? = null
    var price = 0.0
    var symbol: String? = null
    private lateinit var _binding: CoindetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = CoindetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //viewModelDetails = ViewModelProvider(this).get(CoinDetailsViewModel::class.java)
        id = intent.extras?.getString("id")
        name = intent.extras?.getString("name")
        image = intent.extras?.getString("image")
        price = intent.extras?.getDouble("price")!!
        symbol = intent.extras?.getString("symbol")

        populateViews()
    }



    private fun populateViews() {
        _binding.coinItemNameTextView.text = name
        _binding.coinItemPriceTextView.text = price.toString()
        _binding.coinItemSymbolTextView.text = symbol
        Glide.with(_binding.coinItemImageView.context).load(image).into(_binding.coinItemImageView)
    }

}