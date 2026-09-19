package com.netforge.app.crypto

import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2Mode

object Argon2Kdf {
    private val argon = Argon2Kt()

    fun derive(passphrase: String, salt: ByteArray): ByteArray {
        val result = argon.hash(
            mode = Argon2Mode.ARGON2_ID,
            password = passphrase.toByteArray(Charsets.UTF_8),
            salt = salt,
            tCostInIterations = 3,
            mCostInKibibyte = 65536,
            parallelism = 2,
            hashLengthInBytes = 32
        )
        return result.rawHashAsByteArray()
    }
}
