package com.example.taberu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    ImageView optionsImage;
    TextView userLabel;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initFirebaseAuth();

        setOnClick();
        setDisplayName();
    }

    // View By ID
    private void initView() {
        optionsImage = findViewById(R.id.home_imageview_morebutton);
        userLabel = findViewById(R.id.home_texttview_namautama);
    }

    // On Click
    private void setOnClick() {
        optionsImage.setOnClickListener(optionsListener);
    }

    private View.OnClickListener optionsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showLogoutPopUp(v);
        }
    };

    // Firebase Init
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Firebase Auth
    public void setDisplayName() {
        firebaseUser = firebaseAuth.getCurrentUser();
        String name = firebaseUser.getDisplayName();
        userLabel.setText("Halo, " + name + "!");
    }

    // Pop Up
    public void showLogoutPopUp(View v) {

        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(popupListener);
        popupMenu.inflate(R.menu.popup_logout);
        popupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener popupListener =
            new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.menuitem_logout) {
                firebaseAuth.signOut();
                Toast.makeText(getApplicationContext(),
                        "You're logged out",
                        Toast.LENGTH_SHORT
                ).show();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                return true;
            }
            return false;
        }
    };
}