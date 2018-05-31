package com.codecool.webroute;

import com.codecool.webroute.routes.Route;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange requestData) {

        Class route = Route.class;
        Method[] methods = route.getMethods();

        for (Method method:methods) {

            Annotation[] annotations = method.getDeclaredAnnotations();

            for(Annotation annotation : annotations){
                if(annotation instanceof WebRoute){
                    WebRoute myAnnotation = (WebRoute) annotation;
                    if (myAnnotation.value().equals(requestData.getRequestURI().getRawPath())) {
                        try {
                            method.invoke(route.newInstance(), requestData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
