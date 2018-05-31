package com.codecool.webroute;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Server {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started");
    }

}
