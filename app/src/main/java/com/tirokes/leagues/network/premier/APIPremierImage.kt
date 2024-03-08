package com.tirokes.leagues.network.premier

import com.tirokes.leagues.models.apl.table.APLTable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIPremierImage {
    @GET("teamlogo/{team_id}.png")
    fun getImage(@Query("team_id") team_id: String) : Call<APLTable>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIPremierImage {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIPremierImage::class.java)

        }
    }
}