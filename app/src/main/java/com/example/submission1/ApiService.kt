package com.example.submission1

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") q: String
    ): Call<UserResponse>

    @GET("users/salwa")
    fun getDetailUser(
        @Query("salwa") salwa: String
    ): Call<DetailUserResponse>

    @GET("users/salwa/followers")
    fun getFollowers(
        @Query("salwa") salwa: String
    ): Call<List<ItemsItem>>

    @GET("users/salwa/following")
    fun getfollowing(
        @Query("salwa") salwa: String
    ): Call<List<ItemsItem>>
}