// Models.kt
package com.polly.valorantguides.model

data class BuddiesResponse(val data: List<Buddy>)
data class Buddy(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val levels: List<BuddyLevel>
)

data class BuddyLevel(
    val uuid: String,
    val displayName: String,
    val displayIcon: String
)

data class AgentsResponse(val data: List<Agent>)
data class Agent(
    val uuid: String,
    val displayName: String,
    val displayIcon: String
)

data class WeaponsResponse(val data: List<Weapon>)
data class Weapon(
    val uuid: String,
    val displayName: String,
    val displayIcon: String
)