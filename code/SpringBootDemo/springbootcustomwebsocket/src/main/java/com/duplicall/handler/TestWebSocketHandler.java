package com.duplicall.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Description TestWebSocketHandler
 * @Author Sean
 * @Date 2020/5/13 9:55
 * @Version 1.0
 */
public class TestWebSocketHandler extends TextWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 连接成功时触发
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("client connect socket success ,send msg to client ");
        session.sendMessage(new TextMessage("hello client"));
    }

    /**
     * client调用webSocket.send 时，调用该方法
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("get client message " + message.toString());
        session.sendMessage(new TextMessage("server rec client msg  "));
    }

    /**
     * 异常时调用该方法
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("session id [{}] happen error [{}]", session.getId(), exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("client close web socket ");
    }
}
