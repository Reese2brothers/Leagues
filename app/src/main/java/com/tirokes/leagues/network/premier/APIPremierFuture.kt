package com.tirokes.leagues.network.premier

import com.tirokes.leagues.models.apl.games.FutureGames
import com.tirokes.leagues.models.apl.table.APLTable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIPremierFuture {
    @GET("akd4dksxxxdfre-api/apl_games.php")
    fun getPremierFuture() : Call<FutureGames>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIPremierFuture {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIPremierFuture::class.java)

        }
    }
}