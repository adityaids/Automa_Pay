package com.keio.automapay.ui.utility

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ActivityDetailUtilityBinding
import com.keio.automapay.ui.resultpayment.PaymentResultActivity

class DetailUtilityActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_UTILITY = "utility"
    }
    private lateinit var binding: ActivityDetailUtilityBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUtilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val utilityBills = intent.getParcelableExtra<UtilityBillsModel>(EXTRA_UTILITY) as UtilityBillsModel
        binding.tvTitle.text = utilityBills.title
        binding.tvCostValue.text = "${resources.getString(R.string.yen)} ${utilityBills.cost}"
        binding.tvTotal.text = "${resources.getString(R.string.yen)} ${utilityBills.cost}"

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnProcess.setOnClickListener {
            if (utilityBills.title.equals("gas")) {
            } else {

            }
        }
    }

    private fun toSucced(){
        val intent = Intent(this@DetailUtilityActivity, PaymentResultActivity::class.java)
    }

    private fun toFailed(){
        val intent = Intent()
    }
}