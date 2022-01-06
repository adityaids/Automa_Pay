package com.keio.automapay.ui.resultpayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.SplitBillModel
import com.keio.automapay.databinding.ActivityPaymentResultSplitBinding
import com.keio.automapay.ui.history.DetailBillAdapter
import com.keio.automapay.ui.main.MainActivity

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

        Glide.with(this)
            .load(R.drawable.payment_success)
            .into(binding.ivLogo)

        binding.btnBack.setOnClickListener {
            toMain()
        }
    }

    private fun toMain() {
        val intent = Intent(this@PaymentResultSplitActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMain()
    }
}