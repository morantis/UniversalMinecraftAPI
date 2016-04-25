package com.koenv.universalminecraftapi.http.rest;

import java.lang.reflect.Method;

public class RestMethodRegistrationException extends RestException {
    private Method method;

    public RestMethodRegistrationException(String message, Method method) {
        super(method.getName() + ": " + message);
        this.method = method;
    }

    public RestMethodRegistrationException(String message, Throwable cause, Method method) {
        super(method.getName() + ": " + message, cause);
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}