package com.keio.automapay.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.keio.automapay.R
import com.keio.automapay.databinding.ActivityAddExpenditureBinding
import com.keio.automapay.util.Categories

class AddExpenditureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenditureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun addCategories(){
        val categoryArray = Categories.values()
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categoryArray
        ).also { arrayAdapter ->
            binding.menuCategory.setAdapter(arrayAdapter)
        }
    }
}