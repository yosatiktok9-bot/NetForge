package com.netforge.app.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

object Motion {
    const val durationFast = 150
    const val durationBase = 250
    const val durationSlow = 400
    const val durationSlower = 600

    val easeStandard = FastOutSlowInEasing
    val easeEmphasized = CubicBezierEasing(0.2f, 0f, 0f, 1f)

    val springBouncy = spring<Float>(dampingRatio = 0.55f, stiffness = 400f)
    val springGentle = spring<Float>(dampingRatio = 0.8f, stiffness = 300f)

    fun <T> tweenBase() = tween<T>(durationBase, easing = easeStandard)
    fun <T> tweenFast() = tween<T>(durationFast, easing = easeStandard)
    fun <T> tweenSlow() = tween<T>(durationSlow, easing = easeEmphasized)
}
