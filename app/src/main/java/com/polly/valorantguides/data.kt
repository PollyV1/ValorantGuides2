package com.polly.valorantguides

data class BuddiesResponse(
    val data: List<Buddy>
)

data class Buddy(
    val uuid: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val displayIcon: String,
    val assetPath: String,
    val levels: List<Level>
)

data class Level(
    val uuid: String,
    val charmLevel: Int,
    val hideIfNotOwned: Boolean,
    val displayName: String,
    val displayIcon: String,
    val assetPath: String
)
