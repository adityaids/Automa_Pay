package com.keio.automapay.util

import com.keio.automapay.core.data.domain.model.ExpenditureModel
import com.keio.automapay.core.data.source.local.entity.ExpenditureEntity

object DataMapper {
    fun ExpenditureModelToEntity(input: List<ExpenditureModel>): List<ExpenditureEntity>{
        val listEntity = ArrayList<ExpenditureEntity>()
        input.map {
            val expenditureEntity = ExpenditureEntity(
                id = it.id,
                name = it.name,
                category = it.category,
                place = it.place,
                cost = it.cost,
                date = it.date
            )
            listEntity.add(expenditureEntity)
        }
        return listEntity
    }

    fun ExpenditureEntityToModel(input: List<ExpenditureEntity>): List<ExpenditureModel>{
        val listEntity = ArrayList<ExpenditureModel>()
        input.map {
            val expenditureModel = ExpenditureModel(
                id = it.id,
                name = it.name,
                category = it.category,
                place = it.place,
                cost = it.cost,
                date = it.date
            )
            listEntity.add(expenditureModel)
        }
        return listEntity
    }

    fun SingleExpenditureModelToEntity(input: ExpenditureModel): ExpenditureEntity{
        return ExpenditureEntity(
            input.id,
            input.name,
            input.category,
            input.place,
            input.cost,
            input.date
        )
    }

    fun SingleExpenditureEntityToModel(input: ExpenditureEntity): ExpenditureModel{
        return ExpenditureModel(
            input.id,
            input.name,
            input.category,
            input.place,
            input.cost,
            input.date
        )
    }
}