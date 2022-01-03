package com.keio.automapay.core.data

import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.domain.repository.IAutomaRepository
import com.keio.automapay.core.data.source.local.LocalDataSource
import com.keio.automapay.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AutomaRepository(
    private val localDataSource: LocalDataSource
): IAutomaRepository {
    override fun getAllExpenditure(): Flow<List<ExpenditureModel>> {
        return localDataSource.getAllExpenditure().map { DataMapper.ExpenditureEntityToModel(it) }
    }

    override fun getExpenditureById(id: Int): Flow<ExpenditureModel> {
        return localDataSource.getExpenditureById(id).map { DataMapper.SingleExpenditureEntityToModel(it) }
    }

    override suspend fun insertExpenditure(data: List<ExpenditureModel>) =
        localDataSource.insertExpenditure(DataMapper.ExpenditureModelToEntity(data))


    override suspend fun insertSingleExpenditure(data: ExpenditureModel)  =
        localDataSource.insertSingleExpenditure(DataMapper.SingleExpenditureModelToEntity(data))

}