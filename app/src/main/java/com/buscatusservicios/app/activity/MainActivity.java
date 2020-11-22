package com.buscatusservicios.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.buscatusservicios.app.R;
import com.buscatusservicios.app.entity.AuthEntity;
import com.buscatusservicios.app.repository.AuthRepository;
import com.buscatusservicios.app.service.AuthenticationService;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPass;
    private TextView textError;
    private AuthRepository authRepository;
    private CheckBox idSessionStored;
    private AuthenticationService authenticationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.editEmail = findViewById(R.id.editTextEmail);
        this.editPass = findViewById(R.id.editTextPassword);
        this.textError = findViewById(R.id.textError);
        this.idSessionStored = findViewById(R.id.idSessionStored);
        authRepository = new AuthRepository(this);
        this.authenticationService = new AuthenticationService(this);
        AuthEntity auth = authRepository.findByFirts();
        validateAuth(auth, true);
    }

    private void validateAuth(AuthEntity authEntity, boolean sessionStored) {
        if (authEntity != null) {
            authenticationService.genToken(authEntity, sessionStored, textError, idSessionStored, authRepository);
        }
    }

    public void loginUser(View view) throws NoSuchAlgorithmException {
        AuthEntity auth = new AuthEntity(editEmail.getText().toString(), editPass.getText().toString());
        validateAuth(auth, false);
    }
}