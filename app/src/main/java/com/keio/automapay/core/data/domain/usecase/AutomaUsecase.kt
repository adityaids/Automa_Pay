package com.keio.automapay.core.data.domain.usecase

import com.keio.automapay.core.data.domain.model.ExpenditureModel
import kotlinx.coroutines.flow.Flow

interface AutomaUsecase {
    fun getAllExpenditure(): Flow<List<ExpenditureModel>>
    fun getExpenditureById(id: Int): Flow<ExpenditureModel>
    suspend fun insertExpenditure(data: List<ExpenditureModel>)
    suspend fun insertSingleExpenditure(data: ExpenditureModel)
}