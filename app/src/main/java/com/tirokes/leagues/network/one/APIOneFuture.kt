package com.tirokes.leagues.network.one

import com.tirokes.leagues.models.leagueone.games.FutureGames
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIOneFuture {
    @GET("akd4dksxxxdfre-api/1l_games.php")
    fun getOneFuture(): Call<FutureGames>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create(): APIOneFuture {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIOneFuture::class.java)

        }
    }
}