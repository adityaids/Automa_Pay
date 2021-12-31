package com.keio.automapay.core.data.domain.model

data class OrderModel(
    val name: String,
    val total: Int,
    val cost: Int,
    val totalCost: Int
)