package com.keio.automapay.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExpenditureModel(
    val id: Int?,
    val name: String,
    val category: String,
    val place: String,
    val cost: String,
    val date: String
): Parcelable
