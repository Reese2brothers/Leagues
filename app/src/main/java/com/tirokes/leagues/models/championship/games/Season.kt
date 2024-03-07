package com.tirokes.leagues.models.championship.games

data class Season(
    val end_time: String,
    val has_leaguetable: String,
    val has_lineups: String,
    val has_topgoals: String,
    val name: String,
    val sport_id: String,
    val start_time: String
)