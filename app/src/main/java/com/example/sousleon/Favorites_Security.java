package com.example.sousleon;

import android.app.KeyguardManager;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import java.util.ConcurrentModificationException;
import java.util.concurrent.TimeUnit;

public class Favorites_Security extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private String mParamLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_security);

        final String password = "0000";
        Pinview pinview = (Pinview) findViewById(R.id.pinView);
        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                String tempPassword = pinview.getValue();
                if (tempPassword.equals(password)){
                    Intent i = new Intent(Favorites_Security.this, Favorites.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Favorites_Security.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
        boolean b = fingerprintHandler.startAuth(fingerprintManager, null);

        if (b){
            Intent i = new Intent(Favorites_Security.this, Favorites.class);
            startActivity(i);
        }
    }
}
