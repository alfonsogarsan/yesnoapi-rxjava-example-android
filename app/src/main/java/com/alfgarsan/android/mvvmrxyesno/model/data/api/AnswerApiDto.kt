package com.alfgarsan.android.mvvmrxyesno.model.data.api

import com.google.gson.annotations.SerializedName

data class AnswerApiDto (
    @SerializedName("answer")
    val answer: String,
    @SerializedName("forced")
    val forced: Boolean,
    @SerializedName("image")
    val image: String
)