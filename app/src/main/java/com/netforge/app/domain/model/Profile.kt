package com.netforge.app.domain.model

data class Profile(
    val id: Long = 0,
    val name: String = "Default",
    val host: String = "",
    val port: Int = 22,
    val mode: Mode = Mode.DIRECT,
    val sshUser: String = "",
    val sshPass: String = "",
    val sshKey: String = "",
    val sni: String = "",
    val payloadTemplate: String = "",
    val proxy: String = "",
    val dnsPrimary: String = "1.1.1.1",
    val dnsSecondary: String = "8.8.8.8",
    val mtu: Int = 1400,
    val keepalive: Int = 30,
    val udp: Boolean = false,
    val verbose: Boolean = false,
    val favorite: Boolean = false,
    val author: String = "NetForge user",
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
