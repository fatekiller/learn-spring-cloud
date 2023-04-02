package com.learn.lcf;

import io.reactivex.netty.protocol.http.websocket.WebSocketClient;
import org.apache.tomcat.jni.Time;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


// todo 这个有问题，不知道为啥接收不到消息
public class WebClientRunner {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // WebSocket连接地址
        URI uri = URI.create("ws://localhost:2001/websocket");


        // 创建WebSocket连接
        WebSocket webSocket = client.newWebSocketBuilder()
                .buildAsync(uri, new WebSocket.Listener() {
                    @Override
                    public void onOpen(WebSocket webSocket) {
                        System.out.println("WebSocket opened");
                        // 连接建立成功后发送数据
                        webSocket.sendText("Hello, WebSocket", true);
                    }

                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        System.out.println("Received message: " + data);
                        return null;
                    }

                    @Override
                    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
                        System.out.println("Received message: " + data);
                        return null;
                    }

                    @Override
                    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                        System.out.println("WebSocket closed");
                        return null;
                    }

                    @Override
                    public void onError(WebSocket webSocket, Throwable error) {
                        System.out.println("WebSocket error: " + error.getMessage());
                    }
                }).join();

        /*CompletableFuture<String> t = CompletableFuture.supplyAsync( ()-> {
            try {
                Thread.sleep(1000);
                System.out.println(123);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "1234";
        });
        t.join();*/

        // 在连接上添加错误处理程序
        // webSocket.abortOnError(Throwable::printStackTrace);
        Thread.sleep(1000 * 1000);
    }
}
