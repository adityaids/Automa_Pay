package com.keio.automapay.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.core.data.domain.usecase.AutomaUsecase
import com.keio.automapay.util.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(private val automaUsecase: AutomaUsecase): ViewModel() {
    fun insertAllExpenditure() {
        val data = DummyData.getDummyExpenditure()
        viewModelScope.launch(Dispatchers.IO) {
            automaUsecase.insertExpenditure(data)
        }
    }
}