package com.restapi.javacrudapicrud.viewers;

public class successMessageViewer {
    private String message;

    public String userPostsuccess(String userName) {
        return message = String.format("The user %s was succesfully created.", userName);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
