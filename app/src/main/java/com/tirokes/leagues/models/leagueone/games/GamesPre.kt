package com.tirokes.leagues.models.leagueone.games

data class GamesPre(
    val away: Away,
    val bet365_id: String,
    val game_id: String,
    val home: Home,
    val league: League,
    val time: String,
    val time_status: String
)