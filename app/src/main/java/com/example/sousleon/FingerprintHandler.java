package com.example.sousleon;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private boolean isTrue = false;

    public FingerprintHandler(Context tContext) {
        context = tContext;
    }

    public boolean startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

        return isTrue;
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        Toast.makeText(context, "Fingerprint Error Code: "+errorCode+"\nFingerprint Error: "+errString, Toast.LENGTH_SHORT).show();
        super.onAuthenticationError(errorCode, errString);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(context, "Fingerprint Success", Toast.LENGTH_SHORT).show();
        isTrue = true;

        super.onAuthenticationSucceeded(result);
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(context, "Fingerprint Failed", Toast.LENGTH_SHORT).show();
        super.onAuthenticationFailed();
    }
}
