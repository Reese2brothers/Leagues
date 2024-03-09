package com.tirokes.leagues.network.one

import com.tirokes.leagues.models.players.Players
import com.tirokes.leagues.network.championship.APIChampPlayers
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIOnePlayers {
    @GET("akd4dksxxxdfre-api/team_players.php?team=")
    fun getOnePlayers(@Query("team") team: String) : Call<Players>

    companion object {

        private var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIOnePlayers {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIOnePlayers::class.java)

        }
    }
}