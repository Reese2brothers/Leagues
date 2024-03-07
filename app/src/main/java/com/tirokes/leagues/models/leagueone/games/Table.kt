package com.tirokes.leagues.models.leagueone.games

data class Table(
    val currentround: String,
    val groupname: Any,
    val maxrounds: String,
    val name: String,
    val rows: List<Row>
)