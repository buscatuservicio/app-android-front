package com.buscatusservicios.app.model;

import com.google.gson.annotations.SerializedName;

public class GenericErrorModel {
    @SerializedName("statusHttp")
    private int statusHttp;
    @SerializedName("messageError")
    private String messageError;

    public int getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(int statusHttp) {
        this.statusHttp = statusHttp;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
