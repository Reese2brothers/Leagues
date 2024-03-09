package com.tirokes.leagues.network.championship

import com.tirokes.leagues.models.players.Players
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIChampPlayers {
    @GET("akd4dksxxxdfre-api/team_players.php?team=")
    fun getChampPlayers(@Query("team") team: String) : Call<Players>

    companion object {

        private var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIChampPlayers {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIChampPlayers::class.java)

        }
    }
}