package com.keio.automapay.ui.resultpayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.SplitBillModel
import com.keio.automapay.databinding.ActivityPaymentResultSplitBinding
import com.keio.automapay.ui.history.DetailBillAdapter

class PaymentResultSplitActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_TOTAL = "extra_total"
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityPaymentResultSplitBinding
    private val detailBillAdapter = DetailBillAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentResultSplitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataIntent = intent.getParcelableExtra<SplitBillModel>(EXTRA_DATA)
        val dataTotal = intent.getIntExtra(EXTRA_TOTAL, 0)
        binding.rvItem.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = detailBillAdapter
        }

        if (dataIntent != null) {
            detailBillAdapter.setData(dataIntent.listOrder)
            binding.tvDate.text = dataIntent.date
            binding.tvTitle.text = dataIntent.title
            binding.tvTotal.text = dataTotal.toString()
        }
    }
}