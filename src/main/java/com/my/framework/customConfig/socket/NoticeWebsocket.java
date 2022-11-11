package com.my.framework.customConfig.socket;

import com.my.framework.customConfig.error.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 前端通过/notice/{用户id}连接socket
 */
@ServerEndpoint("/notice/{id}")
@Component
public class NoticeWebsocket {

    private static final Logger log = LoggerFactory.getLogger(NoticeWebsocket.class);

    /**
     * 会话唯一标识
     */
    private String sessionKey = null;

    /**
     * 记录所有会话连接 <sessionKey,session>
     */
    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * <id,Set<sessionKey>> 应对同一id多个页面登录的问题
     */
    public static Map<Long, Set<String>> idAndSessionMap = new ConcurrentHashMap<>();

    /**
     * 连接成功后调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") Long id) {
        this.sessionKey = UUID.randomUUID().toString();

        // 通过sid 记录当前连接至map
        clients.put(this.sessionKey, session);

        // 通过map维护同一id下的多个session连接
        Set<String> clientSet = idAndSessionMap.computeIfAbsent(id, k -> new HashSet<>());
        clientSet.add(this.sessionKey);

        log.info(this.sessionKey + "连接开启！");
    }

    /**
     * 发送给所有用户
     */
    public static void sendMessage(String message) {
        for (Session socketSession : NoticeWebsocket.clients.values()) {
            try {
                log.info("向所有人发送消息" + message);
                socketSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据用户id发送给某一个用户
     **/
    public static void sendMessageByUserId(Long userId, String message) {
        if (!StringUtils.isEmpty(userId)) {
            Set<String> clientSet = idAndSessionMap.get(userId);
            if (clientSet != null) {
                for (String sid : clientSet) {
                    Session session = clients.get(sid);
                    if (session != null) {
                        try {
                            session.getBasicRemote().sendText(message);
                            log.info("向用户[id:" + userId + "]发送消息" + message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        String sid = clients.entrySet().stream()
            .filter(stringSessionEntry -> stringSessionEntry.getValue().equals(session))
            .findFirst()
            .orElseThrow(() -> new CustomException("连接已被移除"))
            .getKey();
        Long userId = idAndSessionMap.entrySet().stream()
            .filter(stringSetEntry -> stringSetEntry.getValue().contains(sid))
            .findFirst()
            .orElseThrow(() -> new CustomException("用户socket连接异常"))
            .getKey();

        log.info("收到用户[id:" + userId + "]的消息" + message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info(this.sessionKey + "连接断开！");
        clients.remove(this.sessionKey);
    }

    /**
     * 发生错误时的回调函数
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    /**
     * 判断是否连接的方法
     *
     * @return 是否断开
     */
    public static boolean isServerClose() {
        if (NoticeWebsocket.clients.values().size() == 0) {
            log.info("已断开");
            return true;
        } else {
            log.info("已连接");
            return false;
        }
    }
}


