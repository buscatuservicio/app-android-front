package com.buscatusservicios.app.model;

public class GenTokenResponse {
    private String token;
    private int httpStatus;
    private boolean error;

    public GenTokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "GenTokenResponse{" +
                "token='" + token + '\'' +
                ", httpStatus=" + httpStatus +
                ", error=" + error +
                '}';
    }
}
