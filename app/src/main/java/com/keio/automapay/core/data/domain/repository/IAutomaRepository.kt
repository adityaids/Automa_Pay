package com.keio.automapay.core.data.domain.repository

import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.source.local.entity.ExpenditureEntity
import kotlinx.coroutines.flow.Flow

interface IAutomaRepository {
    fun getAllExpenditure(): Flow<List<ExpenditureModel>>
    fun getExpenditureById(id: Int): Flow<ExpenditureModel>
    suspend fun insertExpenditure(data: List<ExpenditureModel>)
    suspend fun insertSingleExpenditure(data: ExpenditureModel)
}