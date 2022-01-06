package com.keio.automapay.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keio.automapay.core.data.domain.model.BannerModel
import com.keio.automapay.databinding.ItemBannerBinding

class BannerAdapter: RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private val listBaner = ArrayList<BannerModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<BannerModel>){
        listBaner.clear()
        listBaner.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val data = listBaner[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listBaner.size

    inner class BannerViewHolder(
        private val binding: ItemBannerBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(data: BannerModel){
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(binding.ivBanner)
            }
    }
}