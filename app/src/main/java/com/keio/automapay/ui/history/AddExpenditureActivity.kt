package com.keio.automapay.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.keio.automapay.R
import com.keio.automapay.databinding.ActivityAddExpenditureBinding
import com.keio.automapay.util.Categories
import java.text.SimpleDateFormat
import java.util.*

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

    private fun showCalendar(){
        val datePicker = MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Date")
            .build()
        datePicker.show(supportFragmentManager, "Calendar")
        datePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click
            val utc: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            datePicker.selection?.let { date -> utc.timeInMillis = date }
            val format = SimpleDateFormat("dd/MM/yyyy")
            val formatted: String = format.format(utc.time)
            binding.edtCalendar.setText(formatted)
        }
    }
}