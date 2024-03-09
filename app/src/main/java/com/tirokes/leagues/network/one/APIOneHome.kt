package com.tirokes.leagues.network.one

import com.tirokes.leagues.models.leagueone.table.One
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIOneHome {
    @GET("akd4dksxxxdfre-api/1l.php")
    fun getOneHome() : Call<One>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIOneHome {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIOneHome::class.java)

        }
    }
}