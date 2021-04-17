package com.nbondarchuk.graphql.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class GraphQLServer {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLServer.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStarted() {
        System.out.println("GraphQL server started.");
    }
}
