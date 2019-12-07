package dev.adarsh.researcharticles.api

import dev.adarsh.researcharticles.model.Paper
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api{

    private val api:PlosApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.plos.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PlosApiService::class.java)
    }

    fun getPapers(callback: Callback<Paper>){
        val call = api.getSearchQuery()
        call.enqueue(callback)
    }

}