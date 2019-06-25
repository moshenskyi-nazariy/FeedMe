package com.example.nazariy.places.presentation.sign_in.google;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSignInMethod {

    private GoogleSignInClient client;

    public void init(Context context) {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(context, signInOptions);
    }

    public boolean isSignedIn(Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        return account != null;
    }

    public Intent getSignIntent() {
        return client.getSignInIntent();
    }
}
