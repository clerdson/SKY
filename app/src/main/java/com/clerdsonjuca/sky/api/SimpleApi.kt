package com.clerdsonjuca.sky.api

import com.clerdsonjuca.sky.model.All

import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {
    @GET("all.json")
    suspend fun  getAll():Response<List<All>>


    @POST("parking/{postNumber}/out")
    suspend fun getDetails(
        @Path("postNumber")number:String):Response<Boolean>

}