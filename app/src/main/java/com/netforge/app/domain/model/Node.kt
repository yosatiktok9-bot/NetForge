package com.netforge.app.domain.model

data class Node(
    val id: String,
    val name: String,
    val host: String,
    val port: Int,
    val region: String = "",
    val latencyMs: Int = -1,
    val signature: String = ""
)
