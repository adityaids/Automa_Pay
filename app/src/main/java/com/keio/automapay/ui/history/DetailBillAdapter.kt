package com.keio.automapay.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keio.automapay.core.data.domain.model.OrderModel
import com.keio.automapay.databinding.ListDetailBillBinding

class DetailBillAdapter: RecyclerView.Adapter<DetailBillAdapter.DetailBillViewHolder>() {
    private val listData = ArrayList<OrderModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<OrderModel>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBillViewHolder {
        val binding = ListDetailBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailBillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailBillViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class DetailBillViewHolder(
        private val binding: ListDetailBillBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderModel){
            binding.tvTitleValue.text = "${data.name} x ${data.totalOrder}"
            binding.tvTotalValue.text = "${data.cost * data.totalOrder}"
        }
    }
}