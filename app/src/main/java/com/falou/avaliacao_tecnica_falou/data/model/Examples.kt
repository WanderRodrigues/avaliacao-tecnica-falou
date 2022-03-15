package com.falou.avaliacao_tecnica_falou.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Examples(
    @SerializedName("text") val text: String? = "",

) : Parcelable