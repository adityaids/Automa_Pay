package com.keio.automapay.ui.resultpayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ActivityPaymentResultBinding
import com.keio.automapay.ui.main.MainActivity
import java.text.DecimalFormat

class PaymentResultActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_STATUS_PAYMENT = "extra_status_payment"
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var binding: ActivityPaymentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val status = intent.getBooleanExtra(EXTRA_STATUS_PAYMENT, false)
        val data = intent.getParcelableExtra<UtilityBillsModel>(EXTRA_DATA) as UtilityBillsModel
        initView(status, data)

        binding.btnBack.setOnClickListener {
            toMain()
        }
    }

    private fun toMain() {
        val intent = Intent(this@PaymentResultActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMain()
    }

    private fun initView(status: Boolean, data: UtilityBillsModel){
        if (status) {
            Glide.with(this)
                .load(R.drawable.payment_success)
                .into(binding.ivLogo)
            binding.tvNameValue.text = data.title
            binding.tvTitle.text = "Payment Success"
            binding.tvDate.text = data.date
            binding.tvCostValue.text = "${resources.getString(R.string.yen)} ${data.cost}"
            binding.tvTotal.text = "${resources.getString(R.string.yen)} ${data.cost}"
        } else {
            Glide.with(this)
                .load(R.drawable.payment_failed)
                .into(binding.ivLogo)
            binding.tvNameValue.text = data.title
            binding.tvTitle.text = "Payment Failed"
            binding.tvDate.text = data.date
            binding.tvCostValue.text = "${resources.getString(R.string.yen)} ${data.cost}"
            binding.tvTotal.text = "${resources.getString(R.string.yen)} ${data.cost}"
        }
    }
}