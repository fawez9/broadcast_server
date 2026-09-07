package com.websocket.broadcast;

import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Service
public class WebSocketClientService {
    public void startClient(String url) {
        try {
            // 1- create a client and a handler
            StandardWebSocketClient client = new StandardWebSocketClient();
            ClientWebSocketHandler handler = new ClientWebSocketHandler();
            // 2- Connect asynchronously to the server
            WebSocketSession session = client.execute(handler, url).get();
            // 3- Setup keyboard input reader
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected! Type messages below (type 'exit' to quit):");
            System.out.println("> ");

            // 4- Input loop to capture typed text
            while (session.isOpen()) {
                String input = scanner.nextLine();
                // breaking from loop
                if ("exit".equalsIgnoreCase(input.trim())) {
                    session.close();
                    break;
                }
                // if the input not empty
                if (!input.trim().isEmpty()) {
                    // Wraps your terminal text into a WebSocket message (TextMessage)
                    session.sendMessage(new TextMessage(input));
                    System.out.println("> ");
                }

            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}