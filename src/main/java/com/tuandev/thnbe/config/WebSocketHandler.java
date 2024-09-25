package com.tuandev.thnbe.config;

import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class WebSocketHandler extends TextWebSocketHandler {
    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<String> sessionWaiting = new ConcurrentLinkedQueue<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        sendCountUser();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        System.out.println(request);
        JSONObject requestJson = new JSONObject(request);
        String command = requestJson.getString("command");

        switch (command) {
            case "match" -> {
                if (sessionWaiting.isEmpty()) {
                    sessionWaiting.add(session.getId());
                } else {
                    String receiverId = sessionWaiting.poll();
                    WebSocketSession receiverSession = sessions.get(receiverId);
                    if (receiverSession != null && receiverSession.isOpen()) {
                        JSONObject responseInfo = new JSONObject();
                        responseInfo.put("command", "info");
                        responseInfo.put("receiverId", session.getId());
                        receiverSession.sendMessage(new TextMessage(responseInfo.toString()));
                        JSONObject response = new JSONObject();
                        response.put("command", "createOffer");
                        response.put("receiverId", receiverId);
                        session.sendMessage(new TextMessage(response.toString()));
                    }
                }
            }
            case "offer", "answer", "candidate", "filter" -> {
                String receiverId = requestJson.getString("receiverId");
                if (receiverId != null) {
                    WebSocketSession receiverSession = sessions.get(receiverId);
                    if (receiverSession != null && receiverSession.isOpen()) {
                        JSONObject data = requestJson.getJSONObject("data");
                        JSONObject response = new JSONObject();
                        response.put("command", command);
                        response.put("receiverId", receiverId);
                        response.put("data", data);
                        receiverSession.sendMessage(new TextMessage(response.toString()));
                    }
                }
            }
            case "stopCall" -> {
                String receiverId = requestJson.optString("receiverId", null);
                if (receiverId != null && !receiverId.equals(JSONObject.NULL)) {
                    if (sessionWaiting.contains(session.getId())) {
                        sessionWaiting.remove(session.getId());
                    } else {
                        WebSocketSession receiverSession = sessions.get(receiverId);
                        if (receiverSession != null && receiverSession.isOpen()) {
                            JSONObject response = new JSONObject();
                            response.put("command", command);
                            receiverSession.sendMessage(new TextMessage(response.toString()));
                        }
                    }
                }
            }
            case "next" -> {
                String receiverId = requestJson.getString("receiverId");
                WebSocketSession receiverSession = sessions.get(receiverId);
                if (receiverSession != null && receiverSession.isOpen()) {
                    JSONObject response = new JSONObject();
                    response.put("command", command);
                    receiverSession.sendMessage(new TextMessage(response.toString()));
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        sessionWaiting.remove(session.getId());
        sendCountUser();
    }

    private void sendCountUser() throws Exception {
        JSONObject response = new JSONObject();
        response.put("command", "countUser");
        response.put("count", sessions.size());
        sendAllUsers(response.toString());
    }

    private void sendAllUsers(String message) throws Exception {
        synchronized (sessions) {
            for (WebSocketSession session : sessions.values()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

}
