package com.example.component;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.entity.ImSingle;
import com.example.service.ImSingleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/imserverSingle")
@Component
public class WebSocketSingleServer implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(WebSocketSingleServer.class);

    public static final Map<String,Session> sessionMap=new ConcurrentHashMap<>();

    @Resource
    ImSingleService imSingleService;

    static ImSingleService staticImSingleService;

    @OnOpen
    public void onOpen(Session session){
        sessionMap.put(session.getId(),session);
        log.info("[onOpen] 新建链接,session={},当前在线人数：{}",session.getId(),sessionMap.size());
    }

    @OnClose
    public void onClose(Session session){
        sessionMap.remove(session.getId());
        log.info("[onClose] 有一链接关闭，session={},当前在线人数：{}",session.getId(),sessionMap.size());
    }

    @OnMessage
    public void onMessage(String message,Session fromSession){
        log.info("服务器收到消息:{}",message);
        ImSingle imSingle=JSONUtil.toBean(message,ImSingle.class);
        imSingle.setTime(DateUtil.now());
        imSingle.setReaded(0);

        //存到数据库
        staticImSingleService.add(imSingle);
        String jsonStr=JSONUtil.toJsonStr(imSingle);
        this.sendAllMessage(jsonStr);
        log.info("[onMessage] 发送消息：{}",jsonStr);
    }

    @OnError
    public void onError(Session session,Throwable error){
        log.error("[onError] 发生错误",error);
    }

    private void sendMessage(Session fromSession,String message){
        sessionMap.values().forEach(session -> {
            if(fromSession!=session){
                log.info("服务端给客户端[{}]发送消息{}",session.getId(),message);
                try {
                    session.getBasicRemote().sendText(message);
                }catch (IOException e){
                    log.error("服务端发送消息异常",e);
                }
            }
        });
    }

    //服务端发送消息给所有客户端
    private void sendAllMessage(String message){
        try {
            for(Session session:sessionMap.values()){
                log.info("服务端给客户端[{}]发送消息{}",session.getId(),message);
                session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            log.error("服务端发送消息给客户端失败",e);
        }
    }

    @Override
    public void afterPropertiesSet(){
        staticImSingleService=imSingleService;
    }


}
