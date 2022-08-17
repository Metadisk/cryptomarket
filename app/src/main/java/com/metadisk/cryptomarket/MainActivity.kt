package com.metadisk.cryptomarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.metadisk.cryptomarket.databinding.ActivityMainBinding
import com.metadisk.cryptomarket.presentation.adapter.CoinsAdapter
import com.metadisk.cryptomarket.presentation.viewmodel.CoinViewModel
import com.metadisk.cryptomarket.presentation.viewmodel.CoinViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: CoinViewModelFactory

    @Inject
    lateinit var coinsAdapter: CoinsAdapter
    lateinit var  viewModel: CoinViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController


        binding.homeBottomNavView.setupWithNavController(
            navController
        )
        viewModel = ViewModelProvider(this,factory)
            .get(CoinViewModel::class.java)

    }
}