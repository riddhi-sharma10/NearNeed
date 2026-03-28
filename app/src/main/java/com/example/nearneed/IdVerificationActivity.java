package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class IdVerificationActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnSubmit;
    private TextView btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_verification);

        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSkip = findViewById(R.id.btnSkip);

        setupListeners();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        btnSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(this, IdVerifiedActivity.class);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileSuccessActivity.class);
            startActivity(intent);
        });
    }
}
