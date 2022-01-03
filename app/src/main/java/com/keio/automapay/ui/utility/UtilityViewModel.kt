package com.keio.automapay.ui.utility

import androidx.lifecycle.ViewModel
import com.keio.automapay.core.data.domain.model.UtilityBillsModel
import com.keio.automapay.core.data.domain.usecase.AutomaUsecase
import com.keio.automapay.util.DummyData

class UtilityViewModel(private val automaUsecase: AutomaUsecase): ViewModel() {
    fun getAllUtilityBill(): List<UtilityBillsModel> = DummyData.getDummyUtilityBills()
}