package com.example.newdoc2;

public class Message {
    private String text; // message body
    private String memberna;
    private boolean belongsToCurrentUser; // is this message sent by us?

    public Message(String text, String memberna, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberna = memberna;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

    public String getMemberData() {
        return memberna;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}