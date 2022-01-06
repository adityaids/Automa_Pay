package com.keio.automapay.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.databinding.ActivityDetailExpenditureBinding

class DetailExpenditureActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TOTAL = "extra_total"
    }

    private lateinit var binding: ActivityDetailExpenditureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataIntent = intent.getParcelableExtra<ExpenditureModel>(EXTRA_DATA) as ExpenditureModel
        val intIntent = intent.getIntExtra(EXTRA_TOTAL, 0)

        binding.tvTitle.text = dataIntent.name
        binding.tvDate.text = dataIntent.date
        binding.tvSubtitleValue.text = dataIntent.name
        binding.tvPlaceValue.text = dataIntent.place
        binding.tvCostValue.text = dataIntent.cost
        binding.tvTotal.text = intIntent.toString()

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}