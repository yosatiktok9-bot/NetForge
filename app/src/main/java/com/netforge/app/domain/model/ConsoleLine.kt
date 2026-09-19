package com.netforge.app.domain.model

enum class LogLevel { TRACE, DEBUG, INFO, WARN, ERROR }

data class ConsoleLine(
    val id: Long,
    val timestamp: Long,
    val level: LogLevel,
    val tag: String,
    val message: String,
    val stack: String? = null
)
