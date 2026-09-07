package com.websocket.broadcast;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ClientWebSocketHandler extends TextWebSocketHandler {

    /*
     * Triggers as soon as the client successfully completes the WebSocket handshake
     * with the server. It gives visual feedback in the terminal that the connection
     * is live.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("--> Connected to the broadcast server!");
    }

    // Fires every time the server broadcasts a message to this client.
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("\n[Broadcast]: " + message.getPayload());
        System.out.println("> ");
    }

}