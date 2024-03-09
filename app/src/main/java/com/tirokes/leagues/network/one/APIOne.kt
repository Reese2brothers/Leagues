package com.tirokes.leagues.network.one

import com.tirokes.leagues.models.leagueone.table.One
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIOne {
    @GET("akd4dksxxxdfre-api/1l.php")
    fun getOneName(): Call<One>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create(): APIOne {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIOne::class.java)

        }
    }
}