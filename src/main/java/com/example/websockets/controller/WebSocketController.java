package com.example.websockets.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    @MessageMapping("/global/message")
    @SendTo("/topic/messages")
    public String sendMessage(final String message){
        return message;
    }

    @MessageMapping("/user/message")
    @SendToUser("/queue/private-message")
    public String sendPrivateMessage(final String message, final Principal principal){
        return "Sending private message to "+principal.getName()+": "+message;
    }
}
