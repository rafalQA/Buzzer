package com.rafalqa.model;

/**
 * Created by rpiotrowicz on 2017-04-12.
 */
public class MessageDto {

    private String content;
    private String recipient;

    public MessageDto(){

    }

    public MessageDto(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
