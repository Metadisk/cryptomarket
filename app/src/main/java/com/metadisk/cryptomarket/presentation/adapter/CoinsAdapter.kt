package com.metadisk.cryptomarket.presentation.adapter


import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metadisk.cryptomarket.R
import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.util.UIHelper
import com.metadisk.cryptomarket.databinding.FragmentFirstBinding
import com.metadisk.cryptomarket.databinding.ItemCoinsListBinding
import com.metadisk.cryptomarket.presentation.view.CoinDetailsActivity
import java.util.*
import kotlin.collections.ArrayList



class CoinsAdapter(var coins: ArrayList<Coin>, var coinsFilter: ArrayList<Coin> )
    : RecyclerView.Adapter<CoinsAdapter.CoinViewHolder>() {


    /*private val callback = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

    }*/

    //val differ = AsyncListDiffer(this,callback)

    fun updateCoins(newcoins: List<Coin>) {
        coins.clear()
        coins.addAll(newcoins)
        coinsFilter.addAll(newcoins)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinsFilter.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val article = coinsFilter[position]
        holder.bind(article)
    }



    inner class CoinViewHolder(val binding:ItemCoinsListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(coin: Coin){
            binding.coinsItemNameTextView.text = coin.name
            binding.coinsItemSymbolTextView.text = coin.symbol
            binding.coinsItemPriceTextView.text = coin.price.toString()
            UIHelper.showChangePercent(binding.coinsItemChangeTextView, coin.changePercent)
           /* binding.favouriteImageView.setImageResource(
                if (coin.isFavourite) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )*/
            Glide.with(binding.coinsItemImageView.context).
            load(coin.image).
            into(binding.coinsItemImageView)

            /*itemView.favouriteImageView.setOnClickListener {
                onItemClickCallback.onFavouriteClicked(model.symbol)
            }*/
            binding.root.setOnClickListener {

                val context=binding.root.context
                val intent = Intent(context, CoinDetailsActivity::class.java)
                intent.putExtra("id", coin.id)
                intent.putExtra("name", coin.name)
                intent.putExtra("price", coin.price)
                intent.putExtra("symbol", coin.symbol)
                intent.putExtra("image", coin.image)
                context.startActivity(intent)

            }
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val listFilter = ArrayList<Coin>()
                if (p0 == null || p0.isEmpty()) {
                    listFilter.addAll(coinsFilter)
                    coinsFilter = coins
                } else {
                    val filterPattern: String = p0.toString().lowercase(Locale.getDefault()).trim()
                    for (item in coins) {
                        if (item.name.lowercase(Locale.getDefault()).contains(filterPattern) == true) {
                            listFilter.add(item)
                        }
                        coinsFilter = listFilter
                    }
                }
                val results = FilterResults()
                results.values = coinsFilter
                return results;
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

                coinsFilter = p1?.values as ArrayList<Coin>
                notifyDataSetChanged()
            }
        }
    }
}