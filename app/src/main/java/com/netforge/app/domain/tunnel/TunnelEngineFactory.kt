package com.netforge.app.domain.tunnel

import com.netforge.app.domain.model.Mode
import com.netforge.app.domain.model.Profile

object TunnelEngineFactory {
    fun create(profile: Profile): TunnelEngine = when (profile.mode) {
        Mode.DIRECT -> DirectTunnel(profile)
        Mode.WRAPPED -> WrappedTunnel(profile)
        Mode.WRAPPED_PLUS -> WrappedPlusTunnel(profile)
        Mode.SLOW -> SlowTunnel(profile)
        Mode.LIVE -> LiveTunnel(profile)
    }
}
