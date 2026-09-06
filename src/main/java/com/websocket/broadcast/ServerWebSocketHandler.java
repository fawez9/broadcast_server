package com.websocket.broadcast;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ServerWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // Automatically called by Spring as soon as a client finishes the WebSocket
    // handshake and connects successfully
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New client connected : " + session.getId());
    }

    // utomatically called whenever a connected client sends text to the server.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received a message from : " + session.getId() + ": " + payload);
        for (WebSocketSession webSocketSession : sessions) {
            // Ensures we don't echo the message back to the client who sent it. "!"
            if (webSocketSession.isOpen() && !webSocketSession.getId().equals(session.getId())) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    // Automatically called when a client closes their connection, loses internet
    // access, or terminates their application.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Client disconnected : " + session.getId());
    }

}