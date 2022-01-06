package com.keio.automapay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ItemCardBillsBinding

class ExpenditureAdapter: RecyclerView.Adapter<ExpenditureAdapter.ExpenditureViewHolder>() {

    private val listData = ArrayList<ExpenditureModel>()
    var onItemClick: ((ExpenditureModel) -> Unit)? = null

    fun setData(data: List<ExpenditureModel>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenditureViewHolder {
        val binding = ItemCardBillsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenditureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenditureViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ExpenditureViewHolder(
        private val binding: ItemCardBillsBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(data: ExpenditureModel){
                val cost = Integer.parseInt(data.cost) / 1000
                binding.tvBillsNo.text = data.id.toString()
                binding.tvTitle.text = data.name
                binding.tvDate.text = data.date
                binding.tvCost.text = "${itemView.context.resources.getString(R.string.yen)} $cost"+"K"

                when(data.category){
                    "Fashion" -> {
                        Glide.with(itemView.context)
                            .load(R.drawable.fashion)
                            .into(binding.ivType)
                    }
                    "Food" -> {
                        Glide.with(itemView.context)
                            .load(R.drawable.food)
                            .into(binding.ivType)
                    }
                    "Vacation" -> {
                        Glide.with(itemView.context)
                            .load(R.drawable.vacations)
                            .into(binding.ivType)
                    }
                    "Entertainment" -> {
                        Glide.with(itemView.context)
                            .load(R.drawable.entertainment)
                            .into(binding.ivType)
                    }
                }
            }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}