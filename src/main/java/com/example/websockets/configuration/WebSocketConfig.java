package com.example.websockets.configuration;

import com.example.websockets.UserHandshakeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*registry.addEndpoint("/global/message")
                .setHandshakeHandler(new UserHandshakeHandler());
        registry.addEndpoint("/user/message");*/
        registry.addEndpoint("/ws-demo")
                .setHandshakeHandler(new UserHandshakeHandler());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/queue");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    /*@Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                if (StompCommand.SUBSCRIBE.equals(message.getHeaders().get("stompCommand"))) {
                    // do not log subscribe messages
                    return message;
                }
                log.info("Received on server: "+message);
                return message;
            }
        });
    }*/
}
