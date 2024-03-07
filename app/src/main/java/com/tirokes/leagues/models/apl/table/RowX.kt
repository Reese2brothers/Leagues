package com.tirokes.leagues.models.apl.table

data class RowX(
    val change: String,
    val draw: String,
    val extra: Any,
    val goalsagainst: String,
    val goalsfor: String,
    val loss: String,
    val pct: Any,
    val points: String,
    val pos: String,
    val promotion: PromotionX,
    val sort_pos: String,
    val team: TeamX,
    val win: String
)