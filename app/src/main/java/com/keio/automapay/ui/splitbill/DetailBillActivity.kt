package com.keio.automapay.ui.splitbill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.keio.automapay.core.data.domain.model.OrderModel
import com.keio.automapay.core.data.domain.model.SplitBillModel
import com.keio.automapay.databinding.ActivityDetailBillBinding
import com.keio.automapay.ui.history.DetailBillAdapter
import com.keio.automapay.ui.resultpayment.PaymentResultSplitActivity

class DetailBillActivity : AppCompatActivity() {
    companion object{
        const val RESULT_DATA = "result_data"
    }

    private lateinit var binding: ActivityDetailBillBinding
    private val detailBillAdapter = DetailBillAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBills = intent.getParcelableExtra<SplitBillModel>(RESULT_DATA)
        binding.rvItem.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = detailBillAdapter
        }
        var total = 0
        if (dataBills != null) {
            detailBillAdapter.setData(dataBills.listOrder)
            binding.tvTitle.text = dataBills.title
            binding.tvDate.text = dataBills.date
            total = getTotalCost(dataBills.listOrder)
            binding.tvPersonValue.text = dataBills.manyPerson.toString()
            binding.tvTotal.text = total.toString()
            binding.tvTotalPerPerson.text = getCostPerPerson(total, dataBills.manyPerson).toString()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnProcess.setOnClickListener {
            toResultPayment(dataBills!!, getCostPerPerson(total, dataBills.manyPerson))
        }
    }

    private fun toResultPayment(data: SplitBillModel, total: Int) {
        val intent = Intent(this@DetailBillActivity, PaymentResultSplitActivity::class.java).apply {
            putExtra(PaymentResultSplitActivity.EXTRA_DATA, data)
            putExtra(PaymentResultSplitActivity.EXTRA_TOTAL, total)
        }
        startActivity(intent)
    }

    private fun getTotalCost(data: List<OrderModel>): Int{
        return data.map { it.cost * it.totalOrder }.sum()
    }

    private fun getCostPerPerson(total: Int, manyPerson: Int): Int{
        return total / manyPerson
    }
}