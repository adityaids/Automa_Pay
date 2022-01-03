package com.keio.automapay.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keio.automapay.core.data.source.local.entity.ExpenditureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AutomaDao {
    @Query("SELECT * FROM expenditure")
    fun getAllExpenditure(): Flow<List<ExpenditureEntity>>

    @Query("SELECT * FROM expenditure WHERE id = :id")
    fun getExpenditureById(id: Int): Flow<ExpenditureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenditure(data: List<ExpenditureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleExpenditure(data: ExpenditureEntity)
}