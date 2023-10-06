package com.example.websockettest;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enable a simple message broker to handle messages sent to "/all" and "/specific"
        config.enableSimpleBroker("/all", "/specific","/topic");

        // Set the application destination prefix for messages sent from the client to "/app"
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register a WebSocket endpoint accessible at "/ws"
        registry.addEndpoint("/ws")
                // Allow connections from specific origins (you should configure this based on your frontend app)
                .setAllowedOrigins("http://localhost:8080"); // Replace with your frontend's origin
        registry.addEndpoint("/ws").withSockJS();
    }
}

