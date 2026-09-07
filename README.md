# WebSocket Broadcast Server

A dual-mode Java CLI application (Server & Client) built with **Java 21**, **Spring Boot 4.1.1**, and **Picocli** for real-time WebSocket broadcasting.

Inspired by the project specification from [roadmap.sh/projects/broadcast-server](https://roadmap.sh/projects/broadcast-server).

---

## 🚀 Features

- **Dual-Mode CLI**: Run as a standalone WebSocket server or an interactive terminal client using Picocli commands.
- **WebSocket Broadcasting**: Server dynamically manages active client sessions and broadcasts received messages to all connected peers.
- **Full-Duplex Interactive Client**: Client maintains simultaneous receiving (via `ClientWebSocketHandler`) and sending loops (`Scanner` terminal input).
- **Embedded Web Server Control**: Uses Spring Boot `WebApplicationType.NONE` dynamically in client mode to avoid port binding conflicts.

---

## 🛠 Tech Stack

- **Java**: 21
- **Spring Boot**: 4.1.1 (WebSocket Starter)
- **Picocli**: 4.7.7 (`picocli-spring-boot-starter`)
- **Build Tool**: Maven

---

## 📋 Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven wrapper (`./mvnw`) included in the project repository

---

## 🏁 Getting Started

### 1. Clone & Build

```bash
git clone https://github.com/fawez9/broadcast_server
cd broadcast
./mvnw clean compile
```

### 2. Start the Broadcast Server

Run the application in **Server Mode** (listens on `ws://localhost:8080/broadcast`):

```bash
./mvnw spring-boot:run "-Dspring-boot.run.arguments=start"
```

---

### 3. Connect a Client Instance

Open a **new terminal window** and run the application in **Client Mode**:

```bash
./mvnw spring-boot:run "-Dspring-boot.run.arguments=connect"
```

> **Note**: You can open multiple terminal windows with the `connect` command to simulate multiple client sessions exchanging broadcasted messages.

---


## 💬 Usage Instructions

- Once connected as a client, type a message and press `Enter` to broadcast it to all connected terminals.
- Type `exit` in the client terminal to gracefully close the WebSocket connection.

---

## 🔗 Project Reference

- Roadmap.sh Project Spec: [https://roadmap.sh/projects/broadcast-server](https://roadmap.sh/projects/broadcast-server)
