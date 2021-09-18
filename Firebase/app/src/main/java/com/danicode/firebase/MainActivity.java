package com.danicode.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// 14:14

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateAccount;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.edtEmail = (EditText) findViewById(R.id.edt_email);
        this.edtPassword = (EditText) findViewById(R.id.edt_password);
        this.btnCreateAccount = (Button) findViewById(R.id.btn_create_account);
        this.btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        this.initialize();

        this.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });

        this.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(edtEmail.getText().toString(), edtPassword.getText().toString());
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

    /**
     * Método que Inicializa los atributos firebaseAuth y authStateListener
     */
    private void initialize() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            // Escuchar los cambios en la sesión
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "onAuthStateChanged - signed_in " + firebaseUser.getUid());
                    Log.w(TAG, "onAuthStateChanged - signed_in " + firebaseUser.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged - signed_out");
                }
            }
        };
    }

    private void createAccount(String email, String password) {
        this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Cuando termine de crear el usuario...
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(MainActivity.this, "Error al crear cuenta", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void signIn(String email, String password) {
        this.firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Autenticado exitosamente", Toast.LENGTH_SHORT);
                    // Ir a welcome
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish(); // Elimina la actividad anterior
                } else {
                    Toast.makeText(MainActivity.this, "Error al autenticarse", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}