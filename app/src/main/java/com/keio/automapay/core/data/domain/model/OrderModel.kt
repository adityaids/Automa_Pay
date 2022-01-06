package com.keio.automapay.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderModel(
    val name: String,
    val totalOrder: Int,
    val cost: Int
): Parcelable