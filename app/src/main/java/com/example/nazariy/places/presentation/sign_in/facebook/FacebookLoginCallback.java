package com.example.nazariy.places.presentation.sign_in.facebook;

import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

public class FacebookLoginCallback implements FacebookCallback<LoginResult> {
    private static final String TAG = FacebookLoginCallback.class.getSimpleName();

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d(TAG, "onSuccess: " + loginResult.getAccessToken().getToken());
    }

    @Override
    public void onCancel() {
        Log.e(TAG, "onCancel: check permissions");
    }

    @Override
    public void onError(FacebookException error) {
        Log.e(TAG, "onError: ", error);
    }
}
