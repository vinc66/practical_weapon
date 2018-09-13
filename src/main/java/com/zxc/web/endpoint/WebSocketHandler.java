package com.zxc.web.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class WebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> sessionMaps = new ConcurrentHashMap<>();

    public WebSocketHandler() {
    }

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uid = (String) session.getAttributes().get("UID");
        sessionMaps.put(uid, session);
        log.info("connect to the websocket success......当前数量:{}  新入 {}", sessionMaps.size(), uid);
    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("websocket connection closed......");
        String uid = (String) session.getAttributes().get("UID");
        sessionMaps.remove(uid);
        log.info("用户 {} 已退出", uid);
        System.out.println("剩余在线用户" + sessionMaps.size());
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        String uid = (String) session.getAttributes().get("UID");
        sessionMaps.remove(uid);
        sessionMaps.remove(session);
        log.info("websocket connection closed...... {}", uid);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUser(String message) {
        TextMessage textMessage = new TextMessage(message);
        sessionMaps.values().stream().forEach(webSocketSession -> {
            if (webSocketSession != null && webSocketSession.isOpen()) {
                try {
                    webSocketSession.sendMessage(textMessage);
                } catch (IOException e) {
                    throw new RuntimeException("socket发送消息失败",e);
                }
            }
        });
    }
}
