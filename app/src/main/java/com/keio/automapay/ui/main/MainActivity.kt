package com.keio.automapay.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.keio.automapay.R
import com.keio.automapay.databinding.ActivityMainBinding
import com.keio.automapay.ui.adapter.BannerAdapter
import com.keio.automapay.ui.history.ExpenditureActivity
import com.keio.automapay.ui.splitbill.ScanBillActivity
import com.keio.automapay.ui.utility.UtilityActivity
import com.keio.automapay.util.DummyData
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

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

        binding.btnBills.setOnClickListener(this)
        binding.btnHistory.setOnClickListener(this)
        binding.btnPayment.setOnClickListener(this)
        binding.btnScan.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_bills -> {
                toUtility()
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
}