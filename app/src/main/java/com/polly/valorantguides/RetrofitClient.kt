// ApiService.kt
package com.polly.valorantguides.network

import com.polly.valorantguides.BuddiesResponse
import com.polly.valorantguides.model.AgentsResponse
import com.polly.valorantguides.model.WeaponsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v1/agents")
    fun getAgents(): Call<AgentsResponse>

    @GET("v1/buddies")
    fun getBuddies(): Call<BuddiesResponse>

    @GET("v1/weapons")
    fun getWeapons(): Call<WeaponsResponse>
}