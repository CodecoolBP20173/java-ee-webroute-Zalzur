package com.codecool.webroute.routes;

import com.codecool.webroute.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Route {

    public void buildPage(HttpExchange requestData, String response) throws IOException {
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute("/test")
    public void onTest(HttpExchange requestData) throws IOException {
        String response = "You are on the test page.";
        buildPage(requestData, response);
    }

    @WebRoute("/login")
    public void onLogin(HttpExchange requestData) throws IOException {
        String response = "This is the login page.";
        buildPage(requestData, response);
    }

    public void notValidPage(HttpExchange requestData) throws IOException {
        String response = "Page not Found";
        buildPage(requestData, response);
    }

}
