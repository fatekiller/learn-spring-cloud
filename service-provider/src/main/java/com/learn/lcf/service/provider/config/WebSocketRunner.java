package com.learn.lcf.service.provider.config;

import com.learn.lcf.service.provider.controller.MyWebSocketHandler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class WebSocketRunner implements ApplicationRunner {

    private final MyWebSocketHandler server;

    public WebSocketRunner(MyWebSocketHandler server) {
        this.server = server;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                MyWebSocketHandler.sessions.forEach((random, session) -> {
                    try {
                        System.out.println("sending message to" + random);
                        server.sendMessage(random, "Hello from server!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }, 0, 3000);
    }

}
