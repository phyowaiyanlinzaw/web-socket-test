package com.example.websockettest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    SimpMessagingTemplate simpMessagingTemplate;
    private int counter = 0;

    @Autowired
    public NotificationController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }



    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message send(final Message message) {
        // Process the incoming message, access its content
        String receivedMessage = message.getText();

        // Prepare a response message that includes the received content
        String responseText = "Server received your message: " + receivedMessage;

        return new Message(responseText);
    }

    @MessageMapping("/increment")
    public void incrementCounter() {
        // Increment the counter
        counter++;

        System.out.println("Counter: " + counter);

        // Broadcast the updated counter value to all clients
        simpMessagingTemplate.convertAndSend("/topic/counter", counter);
    }

}

