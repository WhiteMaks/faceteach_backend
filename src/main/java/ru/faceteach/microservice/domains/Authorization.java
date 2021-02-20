package ru.faceteach.microservice.domains;

public class Authorization {
    private boolean isAuth;

    public boolean getIsAuth() {
        return isAuth;
    }

    public Authorization setIsAuth(boolean auth) {
        isAuth = auth;
        return this;
    }
}
