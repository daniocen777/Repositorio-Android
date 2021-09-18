package com.danicode.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {
    public static final String TAG = "WelcomeActivity";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private TextView tvUserDetail;
    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.tvUserDetail = (TextView) findViewById(R.id.tv_user_detail);
        this.btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        this.initialize();

        this.btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.firebaseAuth.removeAuthStateListener(authStateListener);
    }

    private void initialize() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            // Escuchar los cambios en la sesión
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    tvUserDetail.setText("IDUser " + firebaseUser.getUid() + " Emial: " + firebaseUser.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged - signed_out");
                }
            }
        };
    }

    private void signOut() {
        this.firebaseAuth.signOut();
    }
}