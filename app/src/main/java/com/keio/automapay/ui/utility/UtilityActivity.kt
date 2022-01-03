package com.keio.automapay.ui.utility

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ActivityUtilityBinding
import com.keio.automapay.ui.adapter.ExpenditureAdapter
import com.keio.automapay.ui.adapter.UtilityAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class UtilityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUtilityBinding
    private val utilityViewModel: UtilityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val utilityAdapter = UtilityAdapter()
        binding.rvBills.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = utilityAdapter
            setHasFixedSize(true)
        }

        utilityAdapter.setData(utilityViewModel.getAllUtilityBill())
        utilityAdapter.onItemClick = {
            toDetailUtility(it)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    private fun toDetailUtility(data: UtilityBillsModel) {
        val intent = Intent(this@UtilityActivity, DetailUtilityActivity::class.java).apply {
            putExtra(DetailUtilityActivity.EXTRA_UTILITY, data)
        }
        startActivity(intent)
    }
}