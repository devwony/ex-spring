package com.whlee.websocket.spv4;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

public class WebSocketHandler extends TextWebSocketHandler {

    private static HashMap<String, WebSocketSession> webSocketSessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.put(session.getId(),session);
        System.out.println("Session Connected: " + session.getId());
        printRemainSessions();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session.getId());
        System.out.println("Session Closed: "+ session.getId());
        printRemainSessions();
    }

    public static void sendMessage(String message) {
        TextMessage msg = new TextMessage(message);
        webSocketSessions.forEach((key, session) -> {
            try {
                System.out.println("Sending a message[" + msg.getPayload() + "] to session["+ session.getId() + "]");
                session.sendMessage(msg);
            } catch (IOException ioEx) {
                // 오류시
                ioEx.printStackTrace();
            }
        });
    }


    private void printRemainSessions(){
        webSocketSessions.keySet().forEach(key -> System.out.println(key));
    }
}
