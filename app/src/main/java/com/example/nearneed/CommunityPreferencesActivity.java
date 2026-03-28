package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CommunityPreferencesActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_preferences);

        btnBack = findViewById(R.id.btnBack);
        btnEnter = findViewById(R.id.btnEnter);

        setupListeners();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        btnEnter.setOnClickListener(v -> {
            Intent intent = new Intent(this, IdVerificationActivity.class);
            startActivity(intent);
        });
    }
}
