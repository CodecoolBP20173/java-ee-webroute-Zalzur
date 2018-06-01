package com.codecool.webroute;

import com.codecool.webroute.routes.Route;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange requestData) {

        Class route = Route.class;
        Method[] methods = route.getMethods();
        boolean wrongPath = true;

        for (Method method : methods) {

            Annotation[] annotations = method.getDeclaredAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof WebRoute) {
                    WebRoute myAnnotation = (WebRoute) annotation;
                    if (myAnnotation.value().equals(requestData.getRequestURI().getRawPath())) {
                        try {
                            method.invoke(route.newInstance(), requestData);
                            wrongPath = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (wrongPath) {
            handleWrongPath(requestData, route);
        }
    }

    public void handleWrongPath(HttpExchange requestData, Class route) {
        try {
            Method notValidPage = route.getDeclaredMethod("notValidPage", HttpExchange.class);
            try {
                notValidPage.invoke(route.newInstance(), requestData);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
