package com.buscatusservicios.app.service;

import android.content.Intent;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.JWT;
import com.buscatusservicios.app.R;
import com.buscatusservicios.app.activity.HomeActivity;
import com.buscatusservicios.app.config.APIClient;
import com.buscatusservicios.app.entity.AuthEntity;
import com.buscatusservicios.app.model.MessageEncryptModel;
import com.buscatusservicios.app.repository.APIRepository;
import com.buscatusservicios.app.repository.AuthRepository;
import com.buscatusservicios.app.utility.Utility;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationService {
    private final APIRepository apiRepository;
    private final AppCompatActivity appCompatActivity;

    public AuthenticationService(AppCompatActivity appCompatActivity) {
        this.apiRepository = APIClient.getClient().create(APIRepository.class);
        this.appCompatActivity = appCompatActivity;
    }

    public void genToken(AuthEntity auth, boolean sessionStored, TextView textError, CheckBox checkBox, AuthRepository authRepository) {

        apiRepository.authentication(Utility.TYPE_AUTHENTICATION.concat(Base64.getEncoder()
                .encodeToString(auth.getEmail()
                        .toString().concat(":")
                        .concat(auth.getPassword())
                        .getBytes())), sessionStored).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    signAuth(response.headers().get(Utility.TOKEN), auth, sessionStored, checkBox.isChecked(), authRepository);
                } else if (response.code() == Utility.BADREQUEST_STATUS) {
                    textError.setText(R.string.message_empty_login);
                } else if (response.code() == Utility.UNAUTHORIZED_STATUS) {
                    textError.setText(R.string.message_error_login);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                textError.setText(t.getMessage());
                call.cancel();
            }
        });
    }

    private void signAuth(String token, final AuthEntity authsrc, boolean sessionStored, boolean isChecked, AuthRepository authRepository) {
        if (sessionStored) {
            JWT jwt = new JWT(token);

            try {
                KeyPair keyPair = RSAService.genKey();
                PublicKey publicKey = RSAService.getKey(jwt.getClaims().get(Utility.PUBLIC_KEY).asString());
                String id = jwt.getClaim(Utility.DOCUMENT_TYPE).asString().concat(jwt.getClaim(Utility.DOCUMENT).asString());
                apiRepository.sign(token, new MessageEncryptModel(RSAService.encryptionMessage(publicKey, new String(Base64.getEncoder().encode(keyPair.getPublic()
                        .getEncoded()))))).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        try {
                            String symetricKey = RSAService.decryptionMessage(keyPair.getPrivate(), response.headers().get(Utility.SYMETRIC_KEY));
                            if (isChecked) {
                                AuthEntity auth = authRepository.findById(id);
                                if (auth == null) {
                                    auth = new AuthEntity(id, authsrc.getEmail(), authsrc.getPassword(), symetricKey);
                                    authRepository.insert(auth);
                                } else {
                                    auth.setSymetricKey(symetricKey);
                                    authRepository.update(auth);
                                }
                            }
                            startHome(symetricKey, token);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        call.cancel();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            startHome(authsrc.getSymetricKey(), token);
        }
    }

    private void startHome(String symetricKey, String token) {
        Intent intent = new Intent(appCompatActivity, HomeActivity.class);
        intent.putExtra(Utility.TOKEN, token);
        intent.putExtra(Utility.SYMETRIC_KEY, symetricKey);
        appCompatActivity.startActivity(intent);
    }
}
