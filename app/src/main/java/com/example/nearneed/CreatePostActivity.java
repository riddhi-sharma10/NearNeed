package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        
        // Handle closing the Create Post activity
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> finish());
        
        // Card click listeners
        findViewById(R.id.cardGigPost).setOnClickListener(v -> {
            startActivity(new Intent(this, CreateGigActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        });
        
        findViewById(R.id.cardCommunityPost).setOnClickListener(v -> {
            android.widget.Toast.makeText(this, "Community posts coming soon!", android.widget.Toast.LENGTH_SHORT).show();
        });
    }
}
