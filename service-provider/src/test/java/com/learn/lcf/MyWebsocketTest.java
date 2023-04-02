package com.learn.lcf;

public class MyWebsocketTest {

    public static void main(String[] args) throws Exception {
        MyWebSocketClient client = new MyWebSocketClient("ws://localhost:2001/websocket");
        client.connect();
        client.awaitConnection();
        client.send("Hello, Server!");
        Thread.sleep(100000);
        client.close();
    }

}
