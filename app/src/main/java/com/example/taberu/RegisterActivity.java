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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button registerButton;
    EditText emailText, passwordText, fullNameText, nickNameText, phoneNumberText;
    ImageView backImage;
    String nickName;
    TextView loginLink;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initFirebaseAuth();
        initFirebaseDatabase();

        setOnClick();
    }

    // View By ID
    private void initView() {
        emailText = findViewById(R.id.register_edittext_email);
        passwordText = findViewById(R.id.register_edittext_password);
        loginLink = findViewById(R.id.register_textview_login);
        registerButton = findViewById(R.id.register_button_daftar);
        fullNameText = findViewById(R.id.register_edittext_namalengkap);
        nickNameText = findViewById(R.id.register_edittext_namapanggilan);
        phoneNumberText = findViewById(R.id.register_edittext_notelepon);
        backImage = findViewById(R.id.register_imageview_backbutton);
    }

    // On Click
    private void setOnClick() {
        loginLink.setOnClickListener(loginIntentListener);
        registerButton.setOnClickListener(registerListener);
        backImage.setOnClickListener(backIntent);
    }

    private View.OnClickListener loginIntentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intentLogin();
        }
    };

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            String fullName = fullNameText.getText().toString();
            nickName = nickNameText.getText().toString();
            String phoneNumber = phoneNumberText.getText().toString();

            register(email, password);

            User user = new User(email, fullName, nickName, phoneNumber);
            insertData(user);
        }
    };

    private View.OnClickListener backIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    };

    // Intent
    private void intentLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void initFirebaseDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://taberu-e5121-default-rtdb.firebaseio.com/");
        reference = firebaseDatabase.getReference("user");
    }

    // Firebase Auth
    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(authRegister);
    }

    private OnCompleteListener<AuthResult> authRegister = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                updateDisplayName(nickName);

                Toast.makeText(getApplicationContext(),
                        "Registration success",
                        Toast.LENGTH_SHORT
                ).show();

                intentLogin();
            }
        }
    };

    public void updateDisplayName(String nickname) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest displayNameUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(nickname).build();

        user.updateProfile(displayNameUpdate);
    }

    // Firebase Database
    public void insertData(User user) {
        reference.child(emailSplitter(user.getEmail()))
            .setValue(user);
    }

    // Utility
    public String emailSplitter(String email) {
        return email.substring(0, email.indexOf("@"));
    }
}