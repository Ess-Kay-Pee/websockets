package com.example.websockets.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class WsController {

    private final SimpMessagingTemplate messagingTemplate;

    private WsController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }
    @PostMapping("/global-message")
    public String sendMessage(@RequestBody final String message){
//        byte[] msg = message.getBytes(StandardCharsets.UTF_8);
        messagingTemplate.convertAndSend("/topic/message",message);
        return "Message send successfully";
    }

    @PostMapping("/private-message")
    public String sendPrivateMessage(@RequestBody final String message, @RequestBody final String id){
        byte[] msg = message.getBytes(StandardCharsets.UTF_8);
        messagingTemplate.convertAndSendToUser(id,"user/queue/private-message", msg);
        return "Private message send successfully";
    }
}
