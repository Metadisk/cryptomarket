package com.metadisk.cryptomarket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metadisk.cryptomarket.data.util.Resource
import com.metadisk.cryptomarket.databinding.FragmentFirstBinding
import com.metadisk.cryptomarket.presentation.adapter.CoinsAdapter
import com.metadisk.cryptomarket.presentation.viewmodel.CoinViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CoinListFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var coinsAdapter: CoinsAdapter
    private lateinit var _binding: FragmentFirstBinding
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var vs_currency = "usd"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        coinsAdapter = (activity as MainActivity).coinsAdapter
        coinsAdapter.setOnItemClickListener {

        }

        initRecyclerView()
        viewNewsList()

    }

    private fun viewNewsList() {

        viewModel.getCoins(vs_currency)
        viewModel.coins.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {

                    hideProgressBar()
                    response.data?.let {
                        coinsAdapter.differ.submitList(it.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        }
    }


    private fun initRecyclerView() {


        _binding.coinsListRecyclerView.apply {
            adapter = coinsAdapter
            _binding.coinsListRecyclerView.layoutManager = GridLayoutManager(context, 2)
            addOnScrollListener(this@CoinListFragment.onScrollListener)

        }
    }

    private fun showProgressBar() {
        isLoading = true
        _binding.coinsListLoading.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        _binding.coinsListLoading.visibility = View.INVISIBLE
    }


    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = _binding.coinsListRecyclerView.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                //page++
                viewModel.getCoins(vs_currency)
                isScrolling = false

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}