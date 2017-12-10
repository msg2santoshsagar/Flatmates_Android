package com.sagar.flatmates.com.sagar.flatmates.com.sagar.flatmates.domain;

/**
 * Created by santosh sagar on 10-12-2017.
 */

public class User {

    private String login;

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}
