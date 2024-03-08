package com.tirokes.leagues.network.premier

import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.models.apl.table.Overall
import com.tirokes.leagues.models.apl.table.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIPremierOverall {
    @GET("akd4dksxxxdfre-api/apl.php")
    fun getOverall() : Call<APLTable>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIPremierOverall {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIPremierOverall::class.java)

        }
    }
}