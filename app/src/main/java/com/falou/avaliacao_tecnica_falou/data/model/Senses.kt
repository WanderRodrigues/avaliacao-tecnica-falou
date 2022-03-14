package com.falou.avaliacao_tecnica_falou.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Senses(
    @SerializedName("definitions") val definitions: List<String>? = listOf(),
    @SerializedName("examples") val examples: List<Examples>? = listOf()
) : Parcelable