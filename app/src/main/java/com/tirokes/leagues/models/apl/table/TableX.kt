package com.tirokes.leagues.models.apl.table

data class TableX(
    val currentround: String,
    val groupname: Any,
    val maxrounds: String,
    val name: String,
    val rows: List<RowX>
)