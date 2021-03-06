import socket

MCAST_GRP = '224.0.0.3'
MCAST_PORT = 8888

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, 2)
sock.sendto("mcast", (MCAST_GRP, MCAST_PORT))
