package com.keio.automapay.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SplitBillModel(
    val id: Int,
    val title: String,
    val listOrder: List<OrderModel>,
    val manyPerson: Int,
    val date: String
): Parcelable