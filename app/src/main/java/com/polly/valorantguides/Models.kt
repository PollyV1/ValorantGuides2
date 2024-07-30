package com.polly.valorantguides.model

import com.google.gson.annotations.SerializedName

// For Agents
data class AgentsResponse(
    @SerializedName("data") val data: List<Agent>
)

data class Agent(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean
)

// For Buddies
data class BuddiesResponse(
    @SerializedName("data") val data: List<Buddy>
)

data class Buddy(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean,
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("assetPath") val assetPath: String,
    @SerializedName("levels") val levels: List<Level>
)

data class Level(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("charmLevel") val charmLevel: Int,
    @SerializedName("hideIfNotOwned") val hideIfNotOwned: Boolean,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("assetPath") val assetPath: String
)

// For Weapons
data class WeaponsResponse(
    @SerializedName("data") val data: List<Weapon>
)

data class Weapon(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean,
    @SerializedName("assetPath") val assetPath: String
)
