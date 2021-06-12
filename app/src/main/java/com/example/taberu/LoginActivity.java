package com.example.taberu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    TextView registerLink;
    EditText emailText, passwordText;
    ImageView backImage;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initFirebaseAuth();

        setOnClick();
    }

    // View By ID
    private void initView() {
        loginButton = findViewById(R.id.login_button_login);
        registerLink = findViewById(R.id.login_textview_daftar);
        emailText = findViewById(R.id.login_edittext_email);
        passwordText = findViewById(R.id.login_edittext_password);
        backImage = findViewById(R.id.login_imageview_backbutton);
    }

    // On Click
    private void setOnClick() {
        loginButton.setOnClickListener(loginListener);
        registerLink.setOnClickListener(registerIntentListener);
        backImage.setOnClickListener(backIntent);
    }

    private View.OnClickListener registerIntentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    };

    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            login(email, password);
        }
    };

    private View.OnClickListener backIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    };

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Firebase Auth
    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(authLogin);
    }

    private OnCompleteListener<AuthResult> authLogin = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(),
                        "Welcome",
                        Toast.LENGTH_SHORT
                ).show();

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            } else {
                Toast.makeText(getApplicationContext(),
                        task.getException().getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    };
    public void pindahregisteractivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}