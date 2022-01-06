package com.keio.automapay.ui.history

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.databinding.ActivityAddExpenditureBinding
import com.keio.automapay.util.Categories
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AddExpenditureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenditureBinding
    private val addExpenditureViewModel: ExpenditureViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCategories()

        binding.btnCalendar.setOnClickListener {
            showCalendar()
        }

        binding.btnAdd.setOnClickListener {
            binding.pgsBar.visibility = View.VISIBLE
            if (validateInput()) {
                addToDb()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun addToDb() {
        val name = binding.edtTitle.text.toString()
        val cost = binding.edtCost.text.toString()
        val place = binding.edtPlace.text.toString()
        val category = binding.menuCategory.text.toString()
        val date = binding.edtCalendar.text.toString()

        val newExpend = ExpenditureModel(
            id = null,
            name = name,
            cost = cost,
            place = place,
            category = category,
            date = date
        )
        addExpenditureViewModel.insertSingleExpenditure(newExpend)
        Handler(mainLooper).postDelayed({
            Toast.makeText(this, "New history added", Toast.LENGTH_LONG).show()
            finish()
        }, 3000L)
    }

    private fun validateInput() :Boolean {
        when {
            binding.edtTitle.text.isNullOrBlank() -> {
                binding.edtTitle.error = "This field can't be empty"
                return false
            }
            binding.menuCategory.text.isNullOrBlank() -> {
                Toast.makeText(this, "You haven't choose category", Toast.LENGTH_LONG).show()
                return false
            }
            binding.edtPlace.text.isNullOrBlank() -> {
                binding.edtPlace.error = "This field can't be empty"
                return false
            }
            binding.edtCost.text.isNullOrBlank() -> {
                binding.edtCost.error = "This field can't be empty"
                return false
            }
            binding.edtCalendar.text.isNullOrBlank() -> {
                Toast.makeText(this, "You haven't select date", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun addCategories(){
        val categoryArray = arrayOf(Categories.FASHION.value, Categories.FOOD.value, Categories.VACATION.value, Categories.ENTERTAINMENT.value)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categoryArray
        ).also { arrayAdapter ->
            binding.menuCategory.setAdapter(arrayAdapter)
        }
    }

    @SuppressLint("SimpleDateFormat")
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