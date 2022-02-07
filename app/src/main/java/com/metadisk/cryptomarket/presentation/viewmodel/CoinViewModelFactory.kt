package com.metadisk.cryptomarket.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metadisk.cryptomarket.domain.usecase.GetCoinsUsecase
import com.metadisk.cryptomarket.presentation.CoinApp

class CoinViewModelFactory(
    private val app: Application,
    private val getCoinsUseCase: GetCoinsUsecase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinViewModel(
            app,
            getCoinsUseCase
        ) as T
    }
}