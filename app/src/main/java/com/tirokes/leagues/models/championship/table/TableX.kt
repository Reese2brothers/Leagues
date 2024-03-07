package com.tirokes.leagues.models.championship.table

data class TableX(
    val currentround: String,
    val groupname: Any,
    val maxrounds: String,
    val name: String,
    val rows: List<RowX>
)