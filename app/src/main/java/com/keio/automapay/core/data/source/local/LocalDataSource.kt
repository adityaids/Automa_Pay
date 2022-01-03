package com.keio.automapay.core.data.source.local

import com.keio.automapay.core.data.source.local.entity.ExpenditureEntity
import com.keio.automapay.core.data.source.local.room.AutomaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val automaDao: AutomaDao) {
    fun getAllExpenditure(): Flow<List<ExpenditureEntity>> = automaDao.getAllExpenditure()
    fun getExpenditureById(id: Int): Flow<ExpenditureEntity> = automaDao.getExpenditureById(id)
    suspend fun insertExpenditure(data: List<ExpenditureEntity>) = automaDao.insertExpenditure(data)
    suspend fun insertSingleExpenditure(data: ExpenditureEntity) = automaDao.insertSingleExpenditure(data)
}