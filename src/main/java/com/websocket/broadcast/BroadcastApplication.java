package com.websocket.broadcast;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

//Tells Spring Boot: "As soon as the Spring context loads, run the run(String... args) method with any terminal arguments passed in."
@SpringBootApplication
public class BroadcastApplication implements CommandLineRunner {
	private final BroadcastCommand broadcastCommand;
	/*
	 * Provided automatically by picocli-spring-boot-starter. It tells Picocli to
	 * use Spring's Application Context to create command classes so dependency
	 * injection (like our constructor injection in ConnectCommand) works
	 * seamlessly.
	 */
	private final IFactory factory;

	public BroadcastApplication(BroadcastCommand broadcastCommand, IFactory factory) {
		this.broadcastCommand = broadcastCommand;
		this.factory = factory;
	}

	public static void main(String[] args) {
		// Disable the Web Server for Client Mode
		boolean isClientMode = Arrays.asList(args).contains("connect");
		new SpringApplicationBuilder(BroadcastApplication.class)
				.web(isClientMode ? WebApplicationType.NONE : WebApplicationType.SERVLET)
				.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Pass CLI arguments directly to Picocli using Spring's bean factory
		/*
		 * Provided automatically by picocli-spring-boot-starter. It tells Picocli to
		 * use Spring's Application Context to create command classes so dependency
		 * injection (like our constructor injection in ConnectCommand) works
		 * seamlessly.
		 */
		int exitCode = new CommandLine(broadcastCommand, factory).execute(args);
		System.exit(exitCode);
	}

}
