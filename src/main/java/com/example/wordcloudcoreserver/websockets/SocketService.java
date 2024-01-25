package com.example.wordcloudcoreserver.websockets;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class SocketService {

    @Autowired
    private SocketIOServer server;

    public void sendMessage(String eventName, String message) {
        Integer size = server.getAllClients().size();
        System.out.println("size: " + size);

        Collection<SocketIOClient> senders = server.getAllClients();
        for (SocketIOClient client : senders) {
            System.out.println("client address: " + client.getRemoteAddress());
            System.out.println("client: " + client.getSessionId());
            System.out.println("");
            client.sendEvent(eventName, message);
        }
    }

}