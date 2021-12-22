package com.keio.automapay.core.data.domain.model

data class SplitBillModel(
    val id: Int,
    val title: String,
    val manyPerson: Int,
    val totalCost: String,
    val costPerPerson: String
)