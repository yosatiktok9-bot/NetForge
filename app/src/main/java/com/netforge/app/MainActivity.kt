package com.netforge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.netforge.app.ui.nav.NetForgeNavHost
import com.netforge.app.ui.theme.NetForgeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as NetForgeApp
        setContent {
            val dawn by app.dataStore.themeDawn.collectAsStateWithLifecycle(false)
            NetForgeTheme(dawn = dawn) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NetForgeNavHost(app = app)
                }
            }
        }
    }
}
