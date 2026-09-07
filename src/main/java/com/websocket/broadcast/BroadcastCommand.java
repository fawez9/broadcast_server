package com.websocket.broadcast;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;

@Component
@Command(name = "broadcast-server", mixinStandardHelpOptions = true, version = "1.0.0", description = "CLI tool to start broadcast server or connect as a client", subcommands = {
        BroadcastCommand.StartCommand.class,
        BroadcastCommand.ConnectCommand.class
})
public class BroadcastCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("please specify a command 'start' or 'connect'");
    }

    // inner class to start server
    @Component
    @Command(name = "start", description = "Start the WebSocket Broadcast Server")
    public static class StartCommand implements Runnable {

        @Override
        public void run() {
            System.out.println("Starting WebSocket Broadcast Server on port 8080 ...");
            System.out.println("Server is running. Press CTRL + C to stop.");

            // Keep the main thread alive while Spring Boot handles WebSockets
            try {
                /*
                 * Since Spring Boot runs the WebSocket server in background threads, this
                 * prevents the command-line application from immediately exiting after printing
                 * the start log.
                 */
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    // inner class to connect client
    @Component
    @Command(name = "connect", description = "Connect client to Broadcast Server")
    public static class ConnectCommand implements Runnable {
        // instead of autowired we used the constructor
        private final WebSocketClientService clientService;

        public ConnectCommand(WebSocketClientService clientService) {
            this.clientService = clientService;
        }

        @Override
        public void run() {
            String serverUrl = "ws://localhost:8080/broadcast";
            System.out.println("Connecting to " + serverUrl + " ...");
            clientService.startClient(serverUrl);
        }

    }
}