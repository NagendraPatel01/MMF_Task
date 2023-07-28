package com.example.kotlincorotinhilt.services

import com.example.kotlincorotinhilt.model.LoginRerspons
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @FormUrlEncoded
    @POST("userLogin ")
    suspend fun Login(@Field("email") email: String, @Field("password") password: String) : Response<LoginRerspons>


}