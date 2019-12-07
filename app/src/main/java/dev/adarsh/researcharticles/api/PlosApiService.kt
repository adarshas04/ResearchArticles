package dev.adarsh.researcharticles.api

import dev.adarsh.researcharticles.model.Paper
import retrofit2.Call
import retrofit2.http.GET

interface PlosApiService {

    @GET("/search?q=title:DNA")
    fun getSearchQuery(): Call<Paper>

}