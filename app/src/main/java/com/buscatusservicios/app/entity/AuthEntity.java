package com.buscatusservicios.app.entity;

public class AuthEntity {
    private String id;
    private String email;
    private String password;
    private String symetricKey;

    public AuthEntity() {
    }

    public AuthEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthEntity(String id, String email, String password, String symetricKey) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.symetricKey = symetricKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSymetricKey() {
        return symetricKey;
    }

    public void setSymetricKey(String symetricKey) {
        this.symetricKey = symetricKey;
    }
}
