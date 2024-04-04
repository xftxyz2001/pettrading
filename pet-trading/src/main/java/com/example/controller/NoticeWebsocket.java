package com.example.controller;

import com.example.domain.Contact;
import com.example.domain.Notice;
import com.example.service.ContactService;
import com.example.service.NoticeService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/api/message/{fromuid}")
public class NoticeWebsocket {

    public static final Gson GSON = new Gson();
    private static NoticeService noticeService;
    private static ContactService contactService;

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        NoticeWebsocket.noticeService = noticeService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        NoticeWebsocket.contactService = contactService;
    }

    private static final CopyOnWriteArraySet<Session> SESSIONS = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    //用来记录sessionId和该session进行绑定
    private static final Map<Long, Session> SESSION_MAP = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("fromuid") Long fromuid) {
        SESSION_MAP.put(fromuid, session);
        SESSIONS.add(session);     //加入set中
        log.info("用户（" + fromuid + ")" + "加入,当前在线人数为" + SESSIONS.size());
        Map<String, Object> qumap = new HashMap<>();
        qumap.put("fromuid", fromuid);
        int noread = contactService.querynoread(qumap);

        Map<String, Object> res = new HashMap<>();
        res.put("type", -1);
        res.put("noread", noread);
        session.getAsyncRemote().sendText(GSON.toJson(res));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SESSIONS.remove(session);  //从set中删除
        log.info("有一连接关闭！当前在线人数为" + SESSIONS.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("fromuid") Long fromuid) {
        //从客户端传过来的数据是json数据，所以这里使用jackson进行转换为SocketMsg对象，
        // 然后通过socketMsg的type进行判断信息类型:
        if (message.equals("heartCheck")) {
            Session fromSession = SESSION_MAP.get(fromuid);
            fromSession.getAsyncRemote().sendText("heartCheck");
        } else {
            Notice notice = GSON.fromJson(message, Notice.class);
            notice.setFromuid(fromuid);
            notice.setDate(new Date());
            notice.setIsread(Notice.ISREAD_UNREAD);
            noticeService.addNotice(notice);
            Contact contact = new Contact();
            contact.setFromuid(notice.getTouid());
            contact.setTouid(notice.getFromuid());
            Map<String, Object> contactmap = new HashMap<>();
            contactmap.put("fromuid", notice.getTouid());
            contactmap.put("touid", notice.getFromuid());
            List<Contact> contacts = contactService.queryContact(contactmap);
            if (contacts.isEmpty()) {
                contact.setNoread(0);
                contactService.addContact(contact);
                contact.setNoread(null);
            }
            contactService.updateContact(contact);
            Session fromSession = SESSION_MAP.get(notice.getFromuid());
            Session toSession = SESSION_MAP.get(notice.getTouid());
            fromSession.getAsyncRemote().sendText(GSON.toJson(notice));
            if (toSession != null) {
                toSession.getAsyncRemote().sendText(GSON.toJson(notice));
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误" + error.getMessage());
    }
}
