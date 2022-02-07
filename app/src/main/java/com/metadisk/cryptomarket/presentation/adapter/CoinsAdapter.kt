package com.metadisk.cryptomarket.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metadisk.cryptomarket.R
import com.metadisk.cryptomarket.data.model.Coin
import com.metadisk.cryptomarket.data.util.UIHelper
import com.metadisk.cryptomarket.databinding.FragmentFirstBinding
import com.metadisk.cryptomarket.databinding.ItemCoinsListBinding

class CoinsAdapter : RecyclerView.Adapter<CoinsAdapter.CoinViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinsListBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class CoinViewHolder(
        val binding:ItemCoinsListBinding):
        RecyclerView.ViewHolder(binding.root){
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

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(coin)
                }
            }
        }
    }

    private var onItemClickListener :((Coin)->Unit)?=null

    fun setOnItemClickListener(listener : (Coin)->Unit){
        onItemClickListener = listener
    }
}