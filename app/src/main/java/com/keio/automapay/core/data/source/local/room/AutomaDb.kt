package com.keio.automapay.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keio.automapay.core.data.source.local.entity.ExpenditureEntity

@Database(entities = [ExpenditureEntity::class], version = 1)
abstract class AutomaDb: RoomDatabase() {
    abstract fun automaDao(): AutomaDao
}