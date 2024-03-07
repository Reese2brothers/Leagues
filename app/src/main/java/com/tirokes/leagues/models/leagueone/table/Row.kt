package com.tirokes.leagues.models.leagueone.table

data class Row(
    val change: String,
    val draw: String,
    val extra: Any,
    val goalsagainst: String,
    val goalsfor: String,
    val loss: String,
    val pct: Any,
    val points: String,
    val pos: String,
    val promotion: Promotion,
    val sort_pos: String,
    val team: Team,
    val win: String
)