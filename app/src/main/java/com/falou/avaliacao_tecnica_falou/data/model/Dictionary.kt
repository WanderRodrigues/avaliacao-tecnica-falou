package com.falou.avaliacao_tecnica_falou.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dictionary(
    @SerializedName("word") val word: String?,
    @SerializedName("results") val results: List<Results>?,

) : Parcelable