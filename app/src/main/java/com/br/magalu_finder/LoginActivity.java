package com.br.magalu_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    private static final String appID = "1944234299165702";
    private LoginButton loginButton;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        //Login Facebook
        LoginButton fbLogin =  (LoginButton) findViewById(R.id.fbLogin);
        callbackManager = CallbackManager.Factory.create();
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Operação cancelada pelo usuário", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Erro ao logar", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void goMainScreen() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        goMainScreen();
    };
}
