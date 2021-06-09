package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    ImageView backImage;
    TextView nameLabel;
    Button logoutButton;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();
        initFirebaseAuth();

        setDisplayName();
        setOnClick();
    }

    // View By ID
    private void initView() {
        backImage = findViewById(R.id.profile_imageview_backbutton);
        nameLabel = findViewById(R.id.profile_textview_nama);
        logoutButton = findViewById(R.id.profile_button_logout);
    }

    // On Click
    private void setOnClick() {
        backImage.setOnClickListener(backIntent);
        logoutButton.setOnClickListener(logoutListener);
    }

    private View.OnClickListener backIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            homeIntent();
        }
    };

    private View.OnClickListener logoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            logout();
            homeIntent();
        }
    };

    // Intent
    private void homeIntent() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Firebase Auth
    public void setDisplayName() {
        firebaseUser = firebaseAuth.getCurrentUser();
        String name = firebaseUser.getDisplayName();
        nameLabel.setText(name);
    }

    public void logout() {
        Toast.makeText(getApplicationContext(),
                "You're now logout from your account",
                Toast.LENGTH_SHORT
        ).show();
        firebaseAuth.signOut();
    }
}