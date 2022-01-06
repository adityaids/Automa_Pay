package com.keio.automapay.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ItemCardBillsBinding

class UtilityAdapter: RecyclerView.Adapter<UtilityAdapter.UtilityViewHolder>() {
    private val listData = ArrayList<UtilityBillsModel>()
    var onItemClick: ((UtilityBillsModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<UtilityBillsModel>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilityViewHolder {
        val binding = ItemCardBillsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UtilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UtilityViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class UtilityViewHolder(
        private val binding: ItemCardBillsBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(data: UtilityBillsModel){
                val cost = Integer.parseInt(data.cost) / 1000
                binding.tvBillsNo.text = data.id.toString()
                binding.tvTitle.text = data.title
                binding.tvDate.text = data.date
                binding.tvCost.text = "${itemView.context.resources.getString(R.string.yen)} $cost"+"K"
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(binding.ivType)
            }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}