package com.buscatusservicios.app.utility;

public interface Utility {
    String TABLE_AUTH_CREATE = "CREATE TABLE auth(id TEXT,email TEXT,password TEXT,symetricKey TEXT)";
    String DROP_TABLE_AUTH = "DROP TABLE IF EXISTS auth";
    String ATTRIBUTE_ID = "id";
    String ATTRIBUTE_EMAIL = "email";
    String ATTRIBUTE_PASSWORD = "password";
    String ATTRIBUTE_SYMETRIC_KEY = "symetricKey";
    String TABLE_AUTH = "auth";
    String PUBLIC_KEY = "publicKey";
    String TOKEN = "Token";
    String SYMETRIC_KEY = "SymetricKey";
    String TYPE_AUTHENTICATION = "Basic ";
    int BADREQUEST_STATUS = 400;
    int UNAUTHORIZED_STATUS = 401;
    int SERVER_ERROR_STATUS = 500;
    int OK_STATUS=200;
    String DOCUMENT_TYPE = "documentType";
    String DOCUMENT = "document";
}
