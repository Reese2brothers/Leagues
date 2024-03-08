package com.tirokes.leagues.network.premier

import com.tirokes.leagues.models.apl.table.APLTable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIPremier {
    @GET("akd4dksxxxdfre-api/apl.php")
    fun getName() : Call<APLTable>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIPremier {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIPremier::class.java)

        }
    }

}