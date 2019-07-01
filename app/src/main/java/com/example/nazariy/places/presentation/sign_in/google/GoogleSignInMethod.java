package com.example.nazariy.places.presentation.sign_in.google;

import android.content.Context;
import android.content.Intent;

import com.example.nazariy.places.presentation.sign_in.SignInMethod;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSignInMethod implements SignInMethod {

    private GoogleSignInClient client;
    private Context context;

    public GoogleSignInMethod(Context context) {
        this.context = context;
    }

    public void init() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(context, signInOptions);
    }

    @Override
    public boolean isSignedIn() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        return account != null;
    }

    @Override
    public String getName() {
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(context);
        return lastSignedInAccount.getDisplayName();
    }

    public Intent getSignIntent() {
        return client.getSignInIntent();
    }
}
