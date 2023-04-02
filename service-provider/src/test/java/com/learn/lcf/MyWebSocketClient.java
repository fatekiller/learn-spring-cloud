package com.learn.lcf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient extends WebSocketClient {

    private CountDownLatch latch;

    public MyWebSocketClient(String serverUri) throws URISyntaxException {
        super(new URI(serverUri));
        latch = new CountDownLatch(1);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connected to server: " + getURI());
        latch.countDown();
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed with code " + code + ". Reason: " + reason);
    }

    @Override
    public void onError(Exception e) {
        System.err.println("Error occurred:" + e);
    }

    public void awaitConnection() throws InterruptedException {
        latch.await(5, TimeUnit.SECONDS);
    }
}

