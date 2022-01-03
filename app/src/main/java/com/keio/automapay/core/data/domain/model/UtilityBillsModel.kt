package com.keio.automapay.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UtilityBillsModel(
    val id: Int,
    val title: String,
    val date: String,
    val cost: String,
    val image: Int
): Parcelable