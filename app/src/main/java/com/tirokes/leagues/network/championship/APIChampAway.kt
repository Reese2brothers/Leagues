package com.tirokes.leagues.network.championship

import com.tirokes.leagues.models.championship.table.CSTable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIChampAway {
    @GET("akd4dksxxxdfre-api/cs.php")
    fun getChampAway() : Call<CSTable>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIChampAway {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIChampAway::class.java)

        }
    }
}