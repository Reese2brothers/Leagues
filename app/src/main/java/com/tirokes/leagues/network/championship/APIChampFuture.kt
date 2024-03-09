package com.tirokes.leagues.network.championship

import com.tirokes.leagues.models.championship.games.FutureGames
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIChampFuture {
    @GET("akd4dksxxxdfre-api/cs_games.php")
    fun getChampFuture(): Call<FutureGames>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create(): APIChampFuture {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIChampFuture::class.java)

        }
    }
}