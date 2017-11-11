package com.yonimor.sporteam.sporteam.com.data;

import java.io.Serializable;

/**
 * Created by TheYoni on 20/10/2017.
 */

public class ConnectionData implements Serializable{
    public static final int OK = 1;
    public static final int NOT_OK = 2;
    public static final int SOMTHING_WRONG = 3;
    public static final int LOGIN = 4;
    public static final int REGISTER = 5;
    public static final int INSERTGAME = 6;

    private int requestCode;
    private String email, password;
    private int worked;
    private User user;
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWorked() {
        return worked;
    }

    public void setWorked(int worked) {
        this.worked = worked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}
