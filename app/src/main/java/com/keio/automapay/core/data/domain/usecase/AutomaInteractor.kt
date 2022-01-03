package com.keio.automapay.core.data.domain.usecase

import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.domain.repository.IAutomaRepository
import kotlinx.coroutines.flow.Flow

class AutomaInteractor(private val automaRepository: IAutomaRepository): AutomaUsecase {
    override fun getAllExpenditure(): Flow<List<ExpenditureModel>> =
        automaRepository.getAllExpenditure()

    override fun getExpenditureById(id: Int): Flow<ExpenditureModel> =
        automaRepository.getExpenditureById(id)

    override suspend fun insertExpenditure(data: List<ExpenditureModel>) =
        automaRepository.insertExpenditure(data)

    override suspend fun insertSingleExpenditure(data: ExpenditureModel) =
        automaRepository.insertSingleExpenditure(data)
}