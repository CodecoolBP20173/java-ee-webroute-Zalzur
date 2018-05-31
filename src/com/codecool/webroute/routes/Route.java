package com.codecool.webroute.routes;

import com.codecool.webroute.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Route {

    @WebRoute("/test")
    public void onTest(HttpExchange requestData) throws IOException {
        String response = "You are on the test page.";
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute("/login")
    public void onLogin(HttpExchange requestData) throws IOException {
        String response = "This is the login page.";
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
