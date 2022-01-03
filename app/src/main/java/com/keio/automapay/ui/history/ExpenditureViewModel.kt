package com.keio.automapay.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.domain.usecase.AutomaUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenditureViewModel(private val automaUsecase: AutomaUsecase): ViewModel() {
    val listExpenditure = automaUsecase.getAllExpenditure().asLiveData()
    fun expenditureById(id: Int): LiveData<ExpenditureModel> = automaUsecase.getExpenditureById(id).asLiveData()
    fun insertSingleExpenditure(data: ExpenditureModel) = viewModelScope.launch(Dispatchers.IO) {
        automaUsecase.insertSingleExpenditure(data)
    }
}