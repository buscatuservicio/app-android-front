package com.buscatusservicios.app.repository;

import com.buscatusservicios.app.model.MessageEncryptModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIRepository {
    @GET("/0C83F57C786A0B4A39EFAB23731C7EBC")
    Call<Void> authentication(@Header("Authorization") String auth, @Header("SessionStored") boolean sessionStored);

    @POST("/0C83F57C786A0B4A39EFAB23731C7EBC")
    Call<Void> sign(@Header("Token") String Token, @Body MessageEncryptModel messageEncryptModel);
}
