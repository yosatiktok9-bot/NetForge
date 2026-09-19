# NetForge

**Private routing, plainly done.**

Android VPN tunnel client — real `VpnService`, real SSH (sshj), real modes.

- Package: `com.netforge.app`
- Version: 1.0
- Min SDK 24 · Target SDK 34
- Kotlin · Jetpack Compose · Material 3
- Theme: Dusk (default) / Dawn

Crafted by Axiom Collective.

## Features

- **Modes:** Direct · Wrapped (TLS) · Wrapped+ (payload) · Slow (DNS) · Live (HTTP Upgrade / WS)
- **Tunnel:** SSH auth → local SOCKS5 → TUN forwarder
- **Profiles:** Room storage, encrypted `.nfg` import/export (Argon2id + AES-256-GCM)
- **Bench:** Where am I, Pingline, Timekeeper, Bridge, Flowmeter, Traceback
- **Console:** live log bus with levels and filters

## Build

```bash
./gradlew :app:assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`

Requires JDK 17, Android SDK 34.

## Use

1. Create a profile (host, port, SSH user/password, mode).
2. On Home, tap **Begin routing** and accept the VPN consent dialog.
3. Traffic routes through the tunnel while phase is **Live**.
4. **End session** tears down SSH, SOCKS, and TUN.

Sample profile password for `app/src/main/res/raw/sample.nfg`:

```
netforge-demo
```

## Server

See **[SERVER_GUIDE.md](SERVER_GUIDE.md)** for Ubuntu 22.04 setup (OpenSSH, stunnel, Squid, 3proxy, UFW, fail2ban).

## CI

`.github/workflows/android.yml` builds on push to `main` and uploads `app-debug.apk`.

## License

Original clean-room implementation. Not derived from FlexNet, HA Tunnel, HTTP Injector, or HTTP Custom.
