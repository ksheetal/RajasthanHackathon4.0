package com.example.sheetal.hp;

/**
 * Created by sheetal on 3/19/2018.
 */

public class Users {

     public String Title;
    public String Message;

    public Users(String title, String message) {
        Title = title;
        Message = message;
    }

    public Users() {
    }

    public String getTitle() {
        return Title;
    }

    public String getMessage() {
        return Message;
    }
}
