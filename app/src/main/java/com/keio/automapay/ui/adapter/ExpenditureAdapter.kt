package com.keio.automapay.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ItemCardBillsBinding

class ExpenditureAdapter: RecyclerView.Adapter<ExpenditureAdapter.ExpenditureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenditureViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ExpenditureViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ExpenditureViewHolder(
        private val binding: ItemCardBillsBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(data: UtilityBillsModel){

            }
    }
}