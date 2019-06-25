package com.example.nazariy.places.presentation.sign_in;

import android.os.Bundle;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.sign_in.facebook.FacebookLoginCallback;
import com.example.nazariy.places.presentation.sign_in.google.GoogleSignInMethod;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    public static final int GOOGLE_REQUEST_CODE = 789;

    private FacebookCallback<LoginResult> facebookCallback = new FacebookLoginCallback();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // TODO: 25.06.19 Check if not signed in

        SignInButton btnGoogleSignIn = findViewById(R.id.btn_google_sign_in);
        btnGoogleSignIn.setOnClickListener(view -> signWithGoogle());
        
        setupFacebookLoginButton();
    }

    private void signWithGoogle() {
        GoogleSignInMethod googleSignInMethod = new GoogleSignInMethod();
        googleSignInMethod.init(this);
        if (!googleSignInMethod.isSignedIn(this)) {
            startActivityForResult(googleSignInMethod.getSignIntent(), GOOGLE_REQUEST_CODE);
        }
    }

    private void setupFacebookLoginButton() {
        LoginButton facebookLoginButton = findViewById(R.id.facebook_login_button);
        CallbackManager callbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setPermissions("email");
        facebookLoginButton.registerCallback(callbackManager, facebookCallback);
    }
}
