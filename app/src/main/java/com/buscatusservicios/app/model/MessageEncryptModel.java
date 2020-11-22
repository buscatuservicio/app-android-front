package com.buscatusservicios.app.model;

public class MessageEncryptModel {
    private String message;

    public MessageEncryptModel(String message) {
        this.message = message;
    }

    public MessageEncryptModel() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
