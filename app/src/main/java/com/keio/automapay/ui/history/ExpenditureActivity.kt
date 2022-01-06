package com.keio.automapay.ui.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.ExpenditureCategoryModel
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.databinding.ActivityExpenditureBinding
import com.keio.automapay.ui.adapter.ExpenditureAdapter
import com.keio.automapay.util.Categories
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class ExpenditureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpenditureBinding
    private val expenditureViewModel: ExpenditureViewModel by viewModel()
    private val expenditureAdapter = ExpenditureAdapter()
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBills.apply {
            layoutManager = LinearLayoutManager(this@ExpenditureActivity)
            adapter = expenditureAdapter
            setHasFixedSize(true)
        }

        expenditureViewModel.listExpenditure.observe(this,{
            initGraph(it)
            expenditureAdapter.setData(it)
        })

        expenditureAdapter.onItemClick = {
            toDetail(total, it)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun toDetail(total: Int, data: ExpenditureModel) {
        val intent = Intent(this@ExpenditureActivity, DetailExpenditureActivity::class.java).apply {
            putExtra(DetailExpenditureActivity.EXTRA_DATA, data)
            putExtra(DetailExpenditureActivity.EXTRA_TOTAL, total)
        }
        startActivity(intent)
    }

    private fun initGraph(data: List<ExpenditureModel>){
        val dataGraph = ArrayList<DataEntry>()
        val filteredList = filteredByCategory(data)
        filteredList.forEach {
            dataGraph.add(ValueDataEntry(it.name, it.percentage))
        }
        val formater = DecimalFormat("###,###")
        total = getSumExpenditure(data)
        val textTotal = "${resources.getString(R.string.yen)} ${formater.format(total)}"
        val pie = AnyChart.pie()
        pie.background().fill("#001679")
        pie.data(dataGraph)
        val title = pie.legend().title()

        title.enabled(true)
        title.fontColor("white")
        title.text("Total $textTotal")
        binding.dashboard.pieChart.setChart(pie)
        binding.dashboard.pieChart.setProgressBar(binding.dashboard.pgsBar)

        binding.btnAdd.setOnClickListener {
            toAdd()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun toAdd() {
        binding.pgsBar1.visibility = View.VISIBLE
        val intent = Intent(this@ExpenditureActivity, AddExpenditureActivity::class.java)
        startActivity(intent)
    }

    private fun getSumExpenditure(data: List<ExpenditureModel>): Int{
        return data.map { Integer.parseInt(it.cost) }.sum()
    }

    private fun filteredByCategory(data: List<ExpenditureModel>): List<ExpenditureCategoryModel>{
        val arrayCategories = Categories.values()
        val dataCategory = ArrayList<ExpenditureCategoryModel>()
        arrayCategories.forEach {
            val filteredData: List<ExpenditureModel> = data.filter { categories -> categories.category == it.value }
            when(it.value){
                "Fashion" -> {
                    val model = ExpenditureCategoryModel(
                        color = R.color.fashion_tag_color,
                        name = filteredData[0].category,
                        percentage = filteredData.size * 100 / data.size
                    )
                    dataCategory.add(model)
                }
                "Food" -> {
                    val model = ExpenditureCategoryModel(
                        color = R.color.food_tag_color,
                        name = filteredData[0].category,
                        percentage = filteredData.size * 100 / data.size
                    )
                    dataCategory.add(model)
                }
                "Vacation"->{
                    val model = ExpenditureCategoryModel(
                        color = R.color.vacation_tag_color,
                        name = filteredData[0].category,
                        percentage = filteredData.size * 100 / data.size
                    )
                    dataCategory.add(model)
                }
                "Entertainment" -> {
                    val model = ExpenditureCategoryModel(
                        color = R.color.entertainment_tag_color,
                        name = filteredData[0].category,
                        percentage = filteredData.size * 100 / data.size
                    )
                    dataCategory.add(model)
                }
            }
        }
        return dataCategory
    }

    override fun onResume() {
        super.onResume()
        binding.pgsBar1.visibility = View.GONE
    }
}