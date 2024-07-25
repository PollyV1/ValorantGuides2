package com.polly.valorantguides

import retrofit2.Call
import retrofit2.http.GET

interface ValorantApiService {
    @GET("v1/buddies")
    fun getBuddies(): Call<BuddiesResponse>

    // Add other endpoints as needed
}
