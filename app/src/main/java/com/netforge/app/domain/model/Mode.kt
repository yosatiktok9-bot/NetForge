package com.netforge.app.domain.model

enum class Mode(val label: String, val description: String) {
    DIRECT("Direct", "Raw TCP socket to host:port"),
    WRAPPED("Wrapped", "TLS socket, SSH on top"),
    WRAPPED_PLUS("Wrapped+", "TLS + payload template first"),
    SLOW("Slow", "UDP 53 DNS tunnel"),
    LIVE("Live", "HTTP Upgrade \u2192 WebSocket \u2192 SSH")
}
