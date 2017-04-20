package com.rafalqa.model;

/**
 * Created by rpiotrowicz on 2017-04-11.
 */
public class Message {

    private String content;
    private String userName;

    public Message(){

    }

    public Message(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
