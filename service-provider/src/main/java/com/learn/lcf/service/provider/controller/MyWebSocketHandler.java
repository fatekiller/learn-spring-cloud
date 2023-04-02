package com.learn.lcf.service.provider.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    public static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String random = UUID.randomUUID().toString(); // 生成一个随机数作为 key
        sessions.put(random, session);
        System.out.println("client connected with random: " + random);
        session.sendMessage(new TextMessage(random)); // 向客户端发送随机数
    }

    public static void sendMessage(String random, String message) throws IOException {
        WebSocketSession session = sessions.get(random);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        } else {
            System.out.println("not send");
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println("received message from client: " + webSocketMessage.getPayload());
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 处理传输错误的逻辑
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 连接关闭后的处理逻辑
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

