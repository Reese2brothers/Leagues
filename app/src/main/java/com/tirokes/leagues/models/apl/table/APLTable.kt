package com.tirokes.leagues.models.apl.table

import com.google.gson.annotations.SerializedName

data class APLTable(
    @SerializedName("results")
    val results: Results,
    val success: Int
)