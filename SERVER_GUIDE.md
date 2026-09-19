# NetForge Server Guide

Backend for Direct / Wrapped / Wrapped+ / Live / Slow modes.

Target: **Ubuntu 22.04 LTS**.

## One-shot setup

```bash
#!/usr/bin/env bash
set -e
apt update && apt -y upgrade
apt -y install openssh-server stunnel4 squid 3proxy ufw fail2ban \
               iptables-persistent openssl

# SSH: key-only root optional
sed -i 's/^#*PermitRootLogin .*/PermitRootLogin prohibit-password/' /etc/ssh/sshd_config
systemctl restart ssh

# TLS wrapper (Wrapped / Wrapped+) — stunnel 443 → 22
openssl req -x509 -nodes -days 3650 -newkey rsa:2048 \
  -keyout /etc/stunnel/netforge.key -out /etc/stunnel/netforge.crt \
  -subj "/CN=cdn.example.com"
cat >/etc/stunnel/stunnel.conf <<'EOF'
pid = /var/run/stunnel4.pid
[netforge]
accept  = 0.0.0.0:443
connect = 127.0.0.1:22
cert    = /etc/stunnel/netforge.crt
key     = /etc/stunnel/netforge.key
EOF
sed -i 's/^ENABLED=.*/ENABLED=1/' /etc/default/stunnel4
systemctl restart stunnel4

# HTTP proxy (optional front)
cat >/etc/squid/squid.conf <<'EOF'
http_port 3128
acl all src 0.0.0.0/0
http_access allow all
via off
forwarded_for delete
EOF
systemctl restart squid

# SOCKS on server side (optional)
mkdir -p /etc/3proxy
cat >/etc/3proxy/3proxy.cfg <<'EOF'
nserver 1.1.1.1
nserver 8.8.8.8
nscache 65536
socks -p1080 -i0.0.0.0
allow *
EOF
systemctl enable 3proxy 2>/dev/null || true
systemctl restart 3proxy 2>/dev/null || true

# Firewall
ufw default deny incoming
ufw default allow outgoing
ufw allow 22/tcp
ufw allow 443/tcp
ufw allow 1080/tcp
ufw allow 3128/tcp
ufw allow 53/udp
ufw --force enable
systemctl enable fail2ban && systemctl restart fail2ban

# Forwarding for exit traffic
sysctl -w net.ipv4.ip_forward=1
echo 'net.ipv4.ip_forward=1' >> /etc/sysctl.conf
IFACE=$(ip route | awk '/default/ {print $5; exit}')
iptables -t nat -A POSTROUTING -o "$IFACE" -j MASQUERADE
netfilter-persistent save
```

## Mode requirements

| Mode        | Server side |
|-------------|-------------|
| **Direct**  | OpenSSH on `host:port` (usually 22). Client does SSH + SOCKS + TUN. |
| **Wrapped** | stunnel (or similar) TLS on 443 → SSH 22. Client does TLS then SSH. |
| **Wrapped+**| Same as Wrapped; client sends payload template bytes before SSH. |
| **Live**    | TCP listener that accepts HTTP Upgrade / WebSocket, then speaks SSH. |
| **Slow**    | iodine or dnstt DNS tunnel server; UDP 53 open. |

## Ports

| Port | Service |
|------|---------|
| 22   | SSH |
| 443  | stunnel → SSH |
| 1080 | 3proxy SOCKS |
| 3128 | Squid |
| 53/udp | DNS tunnel |
