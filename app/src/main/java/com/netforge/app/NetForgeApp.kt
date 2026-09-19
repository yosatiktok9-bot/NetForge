package com.netforge.app

import android.app.Application
import com.netforge.app.data.db.NetForgeDatabase
import com.netforge.app.data.prefs.DataStoreManager
import com.netforge.app.data.repo.ProfileRepository
import com.netforge.app.logging.ConsoleBus

class NetForgeApp : Application() {
    lateinit var database: NetForgeDatabase
        private set
    lateinit var profileRepository: ProfileRepository
        private set
    lateinit var dataStore: DataStoreManager
        private set

    override fun onCreate() {
        super.onCreate()
        database = NetForgeDatabase.get(this)
        profileRepository = ProfileRepository(database)
        dataStore = DataStoreManager(this)
        ConsoleBus.info("App", "NetForge ${BuildConfigHint.VERSION} started")
    }
}

object BuildConfigHint {
    const val VERSION = "1.0"
}
