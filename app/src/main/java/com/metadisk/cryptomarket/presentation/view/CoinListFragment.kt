package com.metadisk.cryptomarket.presentation.view


import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metadisk.cryptomarket.MainActivity
import com.metadisk.cryptomarket.R
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
    private var per_page = 300
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        coinsAdapter = (activity as MainActivity).coinsAdapter

        //coinsAdapter.setOnItemClickListener {

        //}

        initRecyclerView()
        viewNewsList()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.searchitem, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView;
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                coinsAdapter.getFilter().filter(query);
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                coinsAdapter.getFilter().filter(newText)
                return false
            }
        })
    }


    private fun viewNewsList() {

        viewModel.getCoins(vs_currency, per_page, page)
        viewModel.coins.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {

                    hideProgressBar()
                    response.data?.let {
                        //coinsAdapter.differ.submitList(it.toList())
                        coinsAdapter.updateCoins(it)
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
                    page =  page++
                    for (page in 0..page) {
                        viewModel.getCoins(vs_currency, per_page, page)
                    }
                    isScrolling = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}