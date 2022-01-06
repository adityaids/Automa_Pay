package com.keio.automapay.ui.utility

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ActivityDetailUtilityBinding
import com.keio.automapay.ui.resultpayment.PaymentResultActivity
import kotlin.math.cos

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
        binding.tvSubtitleValue.text = "${utilityBills.title} Bill"
        binding.tvCostValue.text = "${resources.getString(R.string.yen)} ${utilityBills.cost}"
        binding.tvTotal.text = "${resources.getString(R.string.yen)} ${utilityBills.cost}"

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnProcess.setOnClickListener {
            val cost = Integer.parseInt(utilityBills.cost)
            showPayDialog(cost, utilityBills)
        }
    }

    private fun toResultPayment(status: Boolean, data: UtilityBillsModel){
        val intent = Intent(this@DetailUtilityActivity, PaymentResultActivity::class.java).apply {
            putExtra(PaymentResultActivity.EXTRA_DATA, data)
            putExtra(PaymentResultActivity.EXTRA_STATUS_PAYMENT, status)
        }
        startActivity(intent)
    }

    private fun showPayDialog(cost: Int, data: UtilityBillsModel){
        val editText = EditText(this)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(editText)

        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle(R.string.input_number)
            .setView(linearLayout)
            .setPositiveButton(R.string.ok) { dialog: DialogInterface, which: Int ->
                val value = Integer.parseInt(editText.text.toString())
                binding.pgsBar.visibility = View.VISIBLE
                isNumberEqualsOrBigger(value, cost, data)
            }
            .setNegativeButton(R.string.cancel){
                    dialog: DialogInterface, which: Int -> dialog.dismiss()
            }
        dialogBuilder.create()
        dialogBuilder.show()
    }

    private fun isNumberEqualsOrBigger(number: Int, cost: Int, data: UtilityBillsModel){
        if (number >= cost) {
            toResultPayment(true, data)
        } else {
            toResultPayment(false, data)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.pgsBar.visibility = View.GONE
    }
}