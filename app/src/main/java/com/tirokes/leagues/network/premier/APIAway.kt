package com.tirokes.leagues.network.premier

import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.models.apl.table.Away
import com.tirokes.leagues.models.apl.table.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIAway {
    @GET("akd4dksxxxdfre-api/apl.php")
    fun getAway() : Call<APLTable>

    companion object {

        var BASE_URL = "https://1win-england-league.store/"

        fun create() : APIAway {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIAway::class.java)

        }
    }
}