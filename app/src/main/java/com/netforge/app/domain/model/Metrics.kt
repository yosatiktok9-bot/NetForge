package com.netforge.app.domain.model

data class Metrics(
    val bytesUp: Long = 0L,
    val bytesDown: Long = 0L,
    val pingMs: Long = 0L,
    val jitterMs: Double = 0.0,
    val connectedSinceMs: Long = 0L
) {
    fun formatUp(): String = formatBytes(bytesUp)
    fun formatDown(): String = formatBytes(bytesDown)

    companion object {
        fun formatBytes(b: Long): String = when {
            b < 1024 -> "$b B"
            b < 1024 * 1024 -> String.format("%.1f KB", b / 1024.0)
            b < 1024 * 1024 * 1024 -> String.format("%.1f MB", b / (1024.0 * 1024))
            else -> String.format("%.2f GB", b / (1024.0 * 1024 * 1024))
        }
    }
}
