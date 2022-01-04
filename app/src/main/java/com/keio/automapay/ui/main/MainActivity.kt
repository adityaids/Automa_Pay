package com.keio.automapay.ui.main

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.databinding.ActivityMainBinding
import com.keio.automapay.ui.adapter.BannerAdapter
import com.keio.automapay.ui.history.ExpenditureActivity
import com.keio.automapay.ui.splitbill.ScanBillActivity
import com.keio.automapay.ui.utility.UtilityActivity
import com.keio.automapay.util.DummyData
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.Format
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bannerAdaper = BannerAdapter()
        binding.rvPromotion.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bannerAdaper
            setHasFixedSize(true)
        }
        val listBanner = DummyData.getBanner(this)
        bannerAdaper.setData(listBanner)

        mainViewModel.insertAllExpenditure()
        mainViewModel.listExpenditure.observe(this, { dataList ->
            setTotal(dataList)
        })

        binding.btnBills.setOnClickListener(this)
        binding.btnHistory.setOnClickListener(this)
        binding.btnPayment.setOnClickListener(this)
        binding.btnScan.setOnClickListener(this)
    }

    private fun setTotal(dataList: List<ExpenditureModel>) {
        var total = 0
        dataList.forEach {
            total += Integer.parseInt(it.cost)
        }
        val formater = DecimalFormat("###,###")
        binding.dashboard.tvTotalExpenditure.text = "${resources.getString(R.string.yen)} ${formater.format(total)}"
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_bills -> {
                showPayDialog()
            }
            R.id.btn_history -> {
                toExpenditure()
            }
            R.id.btn_payment -> {
                toScan()
            }
            R.id.btn_scan -> {
                toScan()
            }
        }
    }

    private fun toUtility(){
        val intent = Intent(this@MainActivity, UtilityActivity::class.java)
        startActivity(intent)
    }

    private fun toExpenditure(){
        val intent = Intent(this@MainActivity, ExpenditureActivity::class.java)
        startActivity(intent)
    }

    private fun toScan(){
        val intent = Intent(this@MainActivity, ScanBillActivity::class.java)
        startActivity(intent)
    }

    private fun showPayDialog(){
        val editText = EditText(this)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(editText)

        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle(R.string.customer_id)
            .setView(linearLayout)
            .setPositiveButton(R.string.ok) { dialog: DialogInterface, which: Int ->
                binding.pgsBar.visibility = View.VISIBLE
                toUtility()
            }
            .setNegativeButton(R.string.cancel){
                    dialog: DialogInterface, which: Int -> dialog.dismiss()
            }
        dialogBuilder.create()
        dialogBuilder.show()
    }

    override fun onResume() {
        super.onResume()
        binding.pgsBar.visibility = View.GONE
    }
}