package com.tirokes.leagues.models.players

data class Table(
    val currentround: String,
    val groupname: Any,
    val maxrounds: String,
    val name: String,
    val rows: List<Row>
)