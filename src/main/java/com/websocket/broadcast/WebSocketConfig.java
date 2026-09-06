package com.websocket.broadcast;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final ServerWebSocketHandler serverWebSocketHandler;

    // Constructor Injection: Because you marked ServerWebSocketHandler with
    // @Component
    public WebSocketConfig(ServerWebSocketHandler serverWebSocketHandler) {
        this.serverWebSocketHandler = serverWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(serverWebSocketHandler, "/broadcast").setAllowedOrigins("*");
    }

}