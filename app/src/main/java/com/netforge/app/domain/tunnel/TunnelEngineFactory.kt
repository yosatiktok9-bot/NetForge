package com.netforge.app.domain.tunnel

import com.netforge.app.domain.model.Mode
import com.netforge.app.domain.model.Profile

object TunnelEngineFactory {
    fun create(profile: Profile): TunnelEngine {
        // Full mode implementations land with the rest of the tree.
        // Stub returns Direct-style engine interface placeholder.
        throw UnsupportedOperationException("Tunnel engines not linked in this build slice")
    }
}
