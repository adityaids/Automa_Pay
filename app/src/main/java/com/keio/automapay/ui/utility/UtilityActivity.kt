package com.keio.automapay.ui.utility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.keio.automapay.R
import com.keio.automapay.databinding.ActivityUtilityBinding
import com.keio.automapay.ui.adapter.ExpenditureAdapter

class UtilityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUtilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val expenditureAdapter = ExpenditureAdapter()
        binding.rvBills.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = expenditureAdapter
            setHasFixedSize(true)
        }
    }
}