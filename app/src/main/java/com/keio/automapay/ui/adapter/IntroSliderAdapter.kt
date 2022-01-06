package com.keio.automapay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.keio.automapay.core.data.domain.model.CarouselModel
import com.keio.automapay.databinding.ItemCarouselBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class IntroSliderAdapter: SliderViewAdapter<IntroSliderAdapter.SliderViewHolder>() {
    private val listData = ArrayList<CarouselModel>()

    fun setData(data: List<CarouselModel>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
    override fun getCount(): Int = listData.size

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        val data = listData[position]
        viewHolder?.bind(data)
    }

    inner class SliderViewHolder(
        private val binding: ItemCarouselBinding
        ): SliderViewAdapter.ViewHolder(binding.root) {
            fun bind(data: CarouselModel){
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(binding.ivCarousel)
                binding.tvTitleCarousel.text = data.title
                binding.tvCaptionCarousel.text = data.subtitle
            }
    }
}