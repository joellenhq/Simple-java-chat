# Simple-java-chat
Application allows multiple clients to communicate. Chat was implemented using UDP protocol communication. 

The communication format consists of operation code, user name and optional information. Every part of communicate is separeted with "|" sign.

Acepted code operations: 

"+" registers a new client with specified name

"-" unregisters client with specified name

"!" server receives text messages from users and sends proper information to users 

"?" sends list of registered users
